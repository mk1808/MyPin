package com.mypin.users.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mypin.users.dtos.RegisterDto;
import com.mypin.users.models.AppUser;
import com.mypin.users.services.IUsersService;

import jakarta.validation.Valid;

public class UsersController implements IUsersController {
	
	private final IUsersService usersService;

	public UsersController(IUsersService usersService) {
		super();
		this.usersService = usersService;
	}

	@Override
	public ResponseEntity<AppUser> register(@Valid RegisterDto registerDto) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<AppUser> get(UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<AppUser> getByEmail(String email) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	
}
