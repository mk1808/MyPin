package com.mypin.pinLists.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mypin.pinLists.clients.IMapsFeignClient;
import com.mypin.pinLists.clients.ISynchronizationFeignClient;
import com.mypin.pinLists.dtos.SynchronizationDto;
import com.mypin.pinLists.exceptions.ResourceNotFoundException;
import com.mypin.pinLists.models.PinList;
import com.mypin.pinLists.repositories.IPinListsRepository;

@Service
public class PinListsService implements IPinListsService {

	private final IPinListsRepository repository;
	private final IMapsFeignClient feignClient;
	private final ISynchronizationFeignClient synchronizationFeignClient;

	public PinListsService(IPinListsRepository repository, IMapsFeignClient feignClient,
			ISynchronizationFeignClient synchronizationFeignClient) {
		super();
		this.repository = repository;
		this.feignClient = feignClient;
		this.synchronizationFeignClient = synchronizationFeignClient;
	}

	@Override
	public PinList save(PinList pinList) {
		feignClient.get(pinList.getMapId());
		pinList = repository.save(pinList);
		patchMapUpdated(pinList);
		return pinList;
	}

	@Override
	public PinList update(PinList pinList, UUID id) {
		PinList existingPinList = get(id);
		existingPinList.setName(pinList.getName());
		existingPinList.setDescription(pinList.getDescription());
		existingPinList.setStyle(pinList.getStyle());
		existingPinList.setOrderNo(pinList.getOrderNo());

		existingPinList = repository.save(existingPinList);
		patchMapUpdated(existingPinList);
		return existingPinList;
	}

	@Override
	public PinList get(UUID id) {
		return repository.findById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException("PinList not found");
		});
	}

	@Override
	public void delete(UUID id) {
		PinList pinList = get(id);
		patchMapUpdated(pinList);
		repository.deleteById(id);
	}

	@Override
	public List<PinList> getByMap(UUID mapId) {
		return repository.findByMapId(mapId);
	}

	@Override
	public void sendUpdateMapByPinList(UUID pinListId) {
		PinList pinList = get(pinListId);
		patchMapUpdated(pinList);
	}

	private void patchMapUpdated(PinList pinList) {
		feignClient.get(pinList.getMapId());
		synchronizePinList(pinList);
	}

	private void synchronizePinList(PinList pinList) {
		SynchronizationDto synchronizationDto = new SynchronizationDto();
		synchronizationDto.channel = pinList.getMapId().toString();
		synchronizationDto.content = ""; // TODO fill pinList
		synchronizationFeignClient.sendSynchronizationMessage(synchronizationDto);
	}
}
