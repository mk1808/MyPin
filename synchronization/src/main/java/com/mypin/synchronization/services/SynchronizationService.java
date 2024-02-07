package com.mypin.synchronization.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.mypin.synchronization.dtos.SynchronizationDto;

@Service
public class SynchronizationService implements ISynchronizationService {

	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;

	@Override
	public void sendSynchronizationMessage(SynchronizationDto synchronizationDto) {
		simpMessagingTemplate.convertAndSend(synchronizationDto.channel, synchronizationDto.content);

	}

}
