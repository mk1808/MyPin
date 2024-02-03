package com.mypin.maps.services;

import java.util.UUID;

import com.mypin.maps.repositories.IMapsRepository;

public class MapsService implements IMapsService {
	
	private final IMapsRepository repository;

	public MapsService(IMapsRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public UUID save(UUID obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID update(UUID obj, UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID get(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
