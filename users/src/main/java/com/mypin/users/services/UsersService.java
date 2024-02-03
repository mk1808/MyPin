package com.mypin.users.services;

import java.util.UUID;

import com.mypin.users.models.AppUser;
import com.mypin.users.repositories.IUsersRepository;

public class UsersService implements IUsersService {
	private final IUsersRepository repository;

	public UsersService(IUsersRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public AppUser save(AppUser obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUser update(AppUser obj, UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUser get(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
