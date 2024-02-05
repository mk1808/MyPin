package com.mypin.pinLists.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.pinLists.dtos.PinListDto;
import com.mypin.pinLists.models.Pin;
import com.mypin.pinLists.models.PinList;
import com.mypin.pinLists.services.ICrudService;
import com.mypin.pinLists.services.IMapPinListsService;

import jakarta.validation.Valid;

@RestController
public class PinListsController implements IPinListsController {

	private final ICrudService<PinList> pinListsCrudService;
	private final ICrudService<Pin> pinCrudService;
	private final IMapPinListsService mMapPinListsService;

	public PinListsController(ICrudService<PinList> pinListsCrudService, ICrudService<Pin> pinCrudService,
			IMapPinListsService mMapPinListsService) {
		super();
		this.pinListsCrudService = pinListsCrudService;
		this.pinCrudService = pinCrudService;
		this.mMapPinListsService = mMapPinListsService;
	}

	@Override
	public ResponseEntity<List<PinListDto>> get(UUID mapId) {
		return ResponseEntity.status(HttpStatus.OK).body(mMapPinListsService.getByMap(mapId));
	}

	@Override
	public ResponseEntity<PinList> createPinList(@Valid PinList pinList) {
		return ResponseEntity.status(HttpStatus.OK).body(pinListsCrudService.save(pinList));
	}

	@Override
	public ResponseEntity<PinList> update(@Valid PinList pinList) {
		return ResponseEntity.status(HttpStatus.OK).body(pinListsCrudService.update(pinList, pinList.getId()));
	}

	@Override
	public ResponseEntity<PinList> delete(UUID pinListId) {
		pinListsCrudService.delete(pinListId);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<Pin> createPin(@Valid Pin pin) {
		return ResponseEntity.status(HttpStatus.OK).body(pinCrudService.save(pin));
	}

	@Override
	public ResponseEntity<Pin> updatePin(@Valid Pin pin) {
		return ResponseEntity.status(HttpStatus.OK).body(pinCrudService.update(pin, pin.getId()));
	}

	@Override
	public ResponseEntity<Pin> deletePin(UUID pinId) {
		pinCrudService.delete(pinId);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
