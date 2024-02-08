package com.mypin.maps.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mypin.maps.dtos.SynchronizationDto;

import jakarta.validation.Valid;

@FeignClient("synchronization")
public interface ISynchronizationFeignClient {
	
	@PostMapping("/api")
	public ResponseEntity<SynchronizationDto> sendSynchronizationMessage(@Valid @RequestBody SynchronizationDto synchronizationDto);

}
