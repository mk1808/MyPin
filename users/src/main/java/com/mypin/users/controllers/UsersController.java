package com.mypin.users.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.users.dtos.RegisterDto;
import com.mypin.users.models.AppUser;
import com.mypin.users.services.ICrudService;
import com.mypin.users.services.IUsersService;

import jakarta.validation.Valid;

@RestController
public class UsersController implements IUsersController {

	private final IUsersService usersService;
	private final ICrudService<AppUser> usersCrudService;

	public UsersController(IUsersService usersService, ICrudService<AppUser> usersCrudService) {
		this.usersService = usersService;
		this.usersCrudService = usersCrudService;
	}

	@Override
	public ResponseEntity<AppUser> register(@Valid RegisterDto registerDto) {
		return ResponseEntity.status(HttpStatus.OK).body(usersService.register(registerDto));
	}

	@Override
	public ResponseEntity<AppUser> get(UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(usersCrudService.get(id));
	}

	@Override
	public ResponseEntity<AppUser> getByEmail(String email) {
		return ResponseEntity.status(HttpStatus.OK).body(usersService.getByEmail(email));
	}

}
