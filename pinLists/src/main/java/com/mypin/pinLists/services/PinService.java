package com.mypin.pinLists.services;

import java.util.UUID;

import com.mypin.pinLists.models.Pin;
import com.mypin.pinLists.repositories.IPinRepository;

public class PinService implements IPinService {

	private final IPinRepository repository;

	public PinService(IPinRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Pin save(Pin obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pin update(Pin obj, UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pin get(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub

	}

}
