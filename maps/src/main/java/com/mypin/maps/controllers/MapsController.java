package com.mypin.maps.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.maps.dtos.SharingDto;
import com.mypin.maps.enums.MapSort;
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
		Map newMap = mapsService.save(map);
		return ResponseEntity.status(HttpStatus.CREATED).body(newMap);
	}

	@Override
	public ResponseEntity<Map> get(UUID id) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		String name = context.getAuthentication().getName();
		System.out.println("name: "+name);
		
		Map map = mapsService.get(id);
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}

	@Override
	public ResponseEntity<List<Map>> search(String title, MapSort sort, Boolean isSharedWithMe, Boolean isMyOwn) {
		List<Map> list = mapsService.search(title, sort, isSharedWithMe, isMyOwn);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@Override
	public ResponseEntity patchTitle(UUID id, String title) {
		mapsService.patchTitle(title, id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity patchUpdated(UUID id) {
		mapsService.patchUpdated(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<Sharing> share(UUID id, @Valid SharingDto sharingDto) {
		Sharing sharing = mapsService.share(id, sharingDto);
		return ResponseEntity.status(HttpStatus.OK).body(sharing);
	}

	@Override
	public ResponseEntity notify(UUID id) {
		mapsService.notify(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
