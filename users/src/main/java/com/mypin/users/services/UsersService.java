package com.mypin.users.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mypin.users.dtos.RegisterDto;
import com.mypin.users.exceptions.PasswordsDoNotMatchException;
import com.mypin.users.exceptions.ResourceNotFoundException;
import com.mypin.users.exceptions.UserWithLoginOrEmailExistsException;
import com.mypin.users.models.AppUser;
import com.mypin.users.repositories.IUsersRepository;

@Service
public class UsersService implements IUsersService {
	private final IUsersRepository repository;

	public UsersService(IUsersRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public AppUser save(AppUser appUser) {
		List<AppUser> existingUser = repository.findByLoginOrEmail(appUser.getLogin(), appUser.getEmail());
		if (!existingUser.isEmpty()) {
			throw new UserWithLoginOrEmailExistsException();
		}
		return repository.save(appUser);
	}

	@Override
	public AppUser update(AppUser appUser, UUID id) {
		AppUser existingUser = get(id);
		existingUser.setLogin(appUser.getLogin());
		existingUser.setEmail(appUser.getEmail());
		return repository.save(existingUser);
	}

	@Override
	public AppUser get(UUID id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	@Override
	public void delete(UUID id) {
		get(id);
		repository.deleteById(id);
	}

	@Override
	public AppUser register(RegisterDto registerDto) {
		if (!registerDto.password.equals(registerDto.repeatPassword)) {
			throw new PasswordsDoNotMatchException();
		}
		AppUser appUser = new AppUser();
		appUser.setLogin(registerDto.login);
		appUser.setEmail(registerDto.email);
		return save(appUser);
	}

	@Override
	public AppUser getByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

}
