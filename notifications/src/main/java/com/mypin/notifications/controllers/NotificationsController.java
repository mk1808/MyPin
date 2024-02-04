package com.mypin.notifications.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.notifications.dtos.NotificationDto;
import com.mypin.notifications.models.Notification;
import com.mypin.notifications.services.INotificationsService;

import jakarta.validation.Valid;

@RestController
public class NotificationsController implements INotificationsController {
	
	private final INotificationsService notificationService;
	
	public NotificationsController(INotificationsService notificationService) {
		this.notificationService = notificationService;
	}

	@Override
	public ResponseEntity<List<Notification>> get() {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity confirm(UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<NotificationDto> create(@Valid NotificationDto map) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
