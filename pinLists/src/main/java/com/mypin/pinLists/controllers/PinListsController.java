package com.mypin.pinLists.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.pinLists.models.Pin;
import com.mypin.pinLists.models.PinList;
import com.mypin.pinLists.services.IPinListsService;

import jakarta.validation.Valid;

@RestController
public class PinListsController implements IPinListsController {

	private final IPinListsService pinListsService;

	public PinListsController(IPinListsService pinListsService) {
		super();
		this.pinListsService = pinListsService;
	}

	@Override
	public ResponseEntity<List<PinList>> get(UUID mapId) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<PinList> createPinList(@Valid PinList pinList) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<PinList> update(@Valid PinList pinList) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<PinList> delete(UUID pinListId) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<Pin> createPin(@Valid Pin pin) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<Pin> updatePin(@Valid Pin pin) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<Pin> deletePin(UUID pinId) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
