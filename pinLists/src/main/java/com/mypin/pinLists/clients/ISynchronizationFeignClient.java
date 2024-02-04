package com.mypin.pinLists.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mypin.pinLists.dtos.SynchronizationDto;

import jakarta.validation.Valid;

@FeignClient("synchronization")
public interface ISynchronizationFeignClient {
	
	@PostMapping
	public ResponseEntity sendSynchronizationMessage(@Valid @RequestBody SynchronizationDto synchronizationDto);


}
