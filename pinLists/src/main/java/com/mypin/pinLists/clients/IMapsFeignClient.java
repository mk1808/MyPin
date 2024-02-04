package com.mypin.pinLists.clients;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mypin.pinLists.dtos.MapDto;



@FeignClient("maps")
public interface IMapsFeignClient {
	
	@PatchMapping("/api/{id}/updated")
	public ResponseEntity patchUpdated(@PathVariable UUID id);
	
	@GetMapping("/api/{id}")
	public ResponseEntity<MapDto> get(@PathVariable UUID id);

}
