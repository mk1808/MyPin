package com.mypin.synchronization.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.synchronization.dtos.SynchronizationDto;
import com.mypin.synchronization.services.ISynchronizationService;

import jakarta.validation.Valid;

@RestController
public class SynchronizationController implements ISynchronizationController {

	private final ISynchronizationService synchronizationService;

	public SynchronizationController(ISynchronizationService synchronizationService) {
		super();
		this.synchronizationService = synchronizationService;
	}

	@Override
	public ResponseEntity sendSynchronizationMessage(@Valid SynchronizationDto synchronizationDto) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
