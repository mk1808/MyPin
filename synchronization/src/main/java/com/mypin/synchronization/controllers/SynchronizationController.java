package com.mypin.synchronization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.mypin.synchronization.dtos.MessageDto;
import com.mypin.synchronization.dtos.SynchronizationDto;
import com.mypin.synchronization.services.ISynchronizationService;

import jakarta.validation.Valid;

@RestController
public class SynchronizationController implements ISynchronizationController {

	private final ISynchronizationService synchronizationService;

	public SynchronizationController(ISynchronizationService synchronizationService) {
		super();
		this.synchronizationService = synchronizationService;
	}

	@Override
	public ResponseEntity sendSynchronizationMessage(@Valid SynchronizationDto synchronizationDto) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	    @Autowired
	    SimpMessagingTemplate simpMessagingTemplate;

	    @MessageMapping("/application")
	    public MessageDto send(final MessageDto message) throws Exception {
	    	SynchronizationDto dto = new SynchronizationDto();
	    	dto.channel = "/all/messages";
	    	dto.content="content1";
	    	synchronizationService.sendSynchronizationMessage(dto);
	        return message;
	    }
	

}
