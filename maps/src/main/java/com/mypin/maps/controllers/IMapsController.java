package com.mypin.maps.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.maps.dtos.SharingDto;
import com.mypin.maps.enums.MapSort;
import com.mypin.maps.models.Map;
import com.mypin.maps.models.Sharing;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping(path = "/api")
@Validated
public interface IMapsController {

	@PostMapping
	public ResponseEntity<Map> create(@Valid @RequestBody Map map);

	@GetMapping("/{id}")
	public ResponseEntity<Map> get(@PathVariable UUID id);

	@GetMapping("/search")
	public ResponseEntity<List<Map>> search(@RequestParam(defaultValue = "", required = false) String title,
			@RequestParam(defaultValue = "UPDATED_DESC", required = false) MapSort sort,
			@RequestParam(defaultValue = "false", required = false) Boolean isSharedWithMe,
			@RequestParam(defaultValue = "false", required = false) Boolean isMyOwn);

	@PatchMapping("/{id}/title")
	public ResponseEntity patchTitle(@PathVariable UUID id, @RequestParam @NotBlank String title);

	@PatchMapping("/{id}/updated")
	public ResponseEntity patchUpdated(@PathVariable UUID id);

	@PostMapping("/{id}/share")
	public ResponseEntity<Sharing> share(@PathVariable UUID id, @Valid @RequestBody SharingDto sharingDto);

	@PostMapping("/{id}/notify")
	public ResponseEntity notify(@PathVariable UUID id);

}
