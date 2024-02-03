package com.mypin.users.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.users.dtos.RegisterDto;
import com.mypin.users.models.AppUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/api")
@Validated
public interface IUsersController {
	
	@PostMapping
	public ResponseEntity<AppUser> register(@Valid @RequestBody RegisterDto registerDto);

	@GetMapping("/{id}")
	public ResponseEntity<AppUser> get(@PathVariable UUID id);
	
	@GetMapping
	public ResponseEntity<AppUser> getByEmail(@RequestParam String email);
	
}
