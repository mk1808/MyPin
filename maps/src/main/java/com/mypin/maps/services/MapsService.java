package com.mypin.maps.services;

import java.util.UUID;

import com.mypin.maps.models.Map;
import com.mypin.maps.repositories.IMapsRepository;

public class MapsService implements IMapsService {
	
	private final IMapsRepository repository;

	public MapsService(IMapsRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Map save(Map obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map update(Map obj, UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map get(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}



}
