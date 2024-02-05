package com.mypin.pinLists.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mypin.pinLists.clients.ISynchronizationFeignClient;
import com.mypin.pinLists.dtos.SynchronizationDto;
import com.mypin.pinLists.exceptions.ResourceNotFoundException;
import com.mypin.pinLists.models.Pin;
import com.mypin.pinLists.models.PinList;
import com.mypin.pinLists.repositories.IPinRepository;

@Service
public class PinService implements IPinService {

	private final IPinRepository repository;
	private final IPinListsService pinListService;
	private final ISynchronizationFeignClient synchronizationFeignClient;

	public PinService(IPinRepository repository, IPinListsService pinListService,
			ISynchronizationFeignClient synchronizationFeignClient) {
		super();
		this.repository = repository;
		this.pinListService = pinListService;
		this.synchronizationFeignClient = synchronizationFeignClient;
	}

	@Override
	public Pin save(Pin pin) {
		pin = repository.save(pin);
		pinListService.sendUpdateMapByPinList(pin.getPinListId());
		synchronizePin(pin);
		return pin;
	}

	@Override
	public Pin update(Pin pin, UUID id) {
		Pin existingPin = get(id);
		existingPin.setName(pin.getName());
		existingPin.setDescription(pin.getDescription());
		existingPin.setOrderNo(pin.getOrderNo());
		existingPin.setLongitude(pin.getLongitude());
		existingPin.setLatitude(pin.getLatitude());
		existingPin.setOpenStreetMapId(pin.getOpenStreetMapId());

		existingPin = repository.save(existingPin);
		pinListService.sendUpdateMapByPinList(existingPin.getPinListId());
		synchronizePin(pin);
		return existingPin;
	}

	@Override
	public Pin get(UUID id) {
		return repository.findById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException("Pin not found");
		});
	}

	@Override
	public void delete(UUID id) {
		Pin pin = get(id);
		pinListService.sendUpdateMapByPinList(pin.getPinListId());
		repository.deleteById(id);
		synchronizePin(pin);
	}

	@Override
	public List<Pin> getByPinList(PinList pinList) {
		return repository.findByPinListIdOrderByOrderNoAsc(pinList.getId());
	}

	private void synchronizePin(Pin pin) {
		PinList pinList = pinListService.get(pin.getPinListId());
		SynchronizationDto synchronizationDto = new SynchronizationDto();
		synchronizationDto.channel = pinList.getMapId().toString();
		synchronizationDto.content = ""; // TODO fill pin
		synchronizationFeignClient.sendSynchronizationMessage(synchronizationDto);
	}

}
