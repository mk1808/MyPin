package com.mypin.maps.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mypin.maps.dtos.NotificationDto;

import jakarta.validation.Valid;

@FeignClient("notifications")
public interface INotificationsFeignClient {
	
	@PostMapping
	public ResponseEntity<NotificationDto> create(@Valid @RequestBody NotificationDto map);

}
