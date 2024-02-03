package com.mypin.pinLists.services;

import java.util.UUID;

import com.mypin.pinLists.models.PinList;
import com.mypin.pinLists.repositories.IPinListsRepository;

public class PinListsService implements IPinListsService {

	private final IPinListsRepository repository;

	public PinListsService(IPinListsRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public PinList save(PinList obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PinList update(PinList obj, UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PinList get(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub

	}

}
