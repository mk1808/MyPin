package com.mypin.pinLists.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.pinLists.dtos.PinListDto;
import com.mypin.pinLists.models.Pin;
import com.mypin.pinLists.models.PinList;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api")
@Validated
public interface IPinListsController {
	
	@GetMapping("/{mapId}")
	public ResponseEntity<List<PinListDto>> get(@PathVariable UUID mapId);

	@PostMapping
	public ResponseEntity<PinList> createPinList(@Valid @RequestBody PinList pinList);
	
	@PutMapping
	public ResponseEntity<PinList> update(@Valid @RequestBody PinList pinList);

	@DeleteMapping("/{pinListId}")
	public ResponseEntity<PinList> delete(@PathVariable UUID pinListId);

	@PostMapping("/pin")
	public ResponseEntity<Pin> createPin(@Valid @RequestBody Pin pin);

	@PutMapping("/pin")
	public ResponseEntity<Pin> updatePin(@Valid @RequestBody Pin pin);
	
	@DeleteMapping("/pin/{pinId}")
	public ResponseEntity<Pin> deletePin(@PathVariable UUID pinId);

}
