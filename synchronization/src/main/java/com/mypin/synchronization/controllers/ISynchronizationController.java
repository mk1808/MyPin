package com.mypin.synchronization.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.synchronization.dtos.SynchronizationDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api")
@Validated
public interface ISynchronizationController {

	@PostMapping
	public ResponseEntity sendSynchronizationMessage(@Valid @RequestBody SynchronizationDto synchronizationDto);
}
