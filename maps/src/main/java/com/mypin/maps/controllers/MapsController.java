package com.mypin.maps.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.maps.dtos.SharingDto;
import com.mypin.maps.models.Map;
import com.mypin.maps.models.Sharing;
import com.mypin.maps.services.IMapsService;

import jakarta.validation.Valid;

@RestController
public class MapsController implements IMapsController {
	
	private final IMapsService mapsService;

	public MapsController(IMapsService mapsService) {
		super();
		this.mapsService = mapsService;
	}

	@Override
	public ResponseEntity<Map> create(@Valid Map map) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<Map> get(UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<List<Map>> search(String name, String sort, String sharedToMe, String myOwn) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity patchName(UUID id, String name) {
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity patchUpdated(UUID id) {
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<Sharing> share(UUID id, @Valid SharingDto sharingDto) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity notify(UUID id) {
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	
	
	

}
