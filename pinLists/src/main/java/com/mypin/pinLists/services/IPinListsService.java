package com.mypin.pinLists.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mypin.pinLists.models.PinList;

@Service
public interface IPinListsService extends ICrudService<PinList> {

	public List<PinList> getByMap(UUID mapId);
	
	public void sendUpdateMapByPinList(UUID pinListId);
}
