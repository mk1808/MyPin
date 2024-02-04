package com.mypin.pinLists.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mypin.pinLists.clients.IMapsFeignClient;
import com.mypin.pinLists.models.PinList;
import com.mypin.pinLists.repositories.IPinListsRepository;

@Service
public class PinListsService implements IPinListsService {

	private final IPinListsRepository repository;
	private final IMapsFeignClient feignClient;

	public PinListsService(IPinListsRepository repository, IMapsFeignClient feignClient) {
		super();
		this.repository = repository;
		this.feignClient = feignClient;
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
