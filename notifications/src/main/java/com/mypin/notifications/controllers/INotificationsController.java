package com.mypin.notifications.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.mypin.notifications.dtos.NotificationDto;
import com.mypin.notifications.models.Notification;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api")
@Validated
public interface INotificationsController {

	@GetMapping
	public ResponseEntity<List<Notification>> get();

	@PatchMapping("/{id}")
	public ResponseEntity confirm(@PathVariable UUID id);

	@PostMapping
	public ResponseEntity<Notification> create(@Valid @RequestBody NotificationDto map);
}
