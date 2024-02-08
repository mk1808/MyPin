package com.mypin.synchronization.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.mypin.synchronization.dtos.SynchronizationDto;

@Service
public class SynchronizationService implements ISynchronizationService {

	private final SimpMessagingTemplate simpMessagingTemplate;
	private final String DEFAULT_CHANNEL = "/all/messages";

	public SynchronizationService(SimpMessagingTemplate simpMessagingTemplate) {
		super();
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@Override
	public void send(SynchronizationDto synchronizationDto) {
		System.out.println(synchronizationDto.toString());
		if (synchronizationDto.channel == null) {
			synchronizationDto.channel = DEFAULT_CHANNEL;
		}

		simpMessagingTemplate.convertAndSend(synchronizationDto.channel, synchronizationDto.content);
	}

}
