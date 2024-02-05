package com.mypin.pinLists.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mypin.pinLists.dtos.PinListDto;
import com.mypin.pinLists.models.PinList;

@Service
public class MapPinListsService implements IMapPinListsService {
	private IPinListsService pinListService;
	private IPinService pinService;

	public MapPinListsService(IPinListsService pinListService, IPinService pinService) {
		super();
		this.pinListService = pinListService;
		this.pinService = pinService;
	}

	@Override
	public List<PinListDto> getByMap(UUID mapId) {
		return pinListService.getByMap(mapId).stream().map(this::mapPinList).toList();
	}

	private PinListDto mapPinList(PinList pinList) {
		PinListDto pinListDto = new PinListDto();
		pinListDto.pinList = pinList;
		pinListDto.pins = pinService.getByPinList(pinList);
		return pinListDto;
	}
}
