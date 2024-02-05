package com.mypin.pinLists.services;

import java.util.List;
import java.util.UUID;

import com.mypin.pinLists.dtos.PinListDto;

public interface IMapPinListsService {

	public List<PinListDto> getByMap(UUID mapId);
}
