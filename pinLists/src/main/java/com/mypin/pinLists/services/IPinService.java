package com.mypin.pinLists.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mypin.pinLists.models.Pin;
import com.mypin.pinLists.models.PinList;

@Service
public interface IPinService extends ICrudService<Pin> {

	public List<Pin> getByPinList(PinList pinList);
}
