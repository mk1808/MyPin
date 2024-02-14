package com.mypin.synchronization.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.mypin.synchronization.dtos.SynchronizationDto;

@Service
public class SynchronizationService implements ISynchronizationService {
	private final static String DEFAULT_CHANNEL = "/all/messages";
	private final static String SPECYFIC_CHANNEL_PREFIX = "/specific/";
	
    Logger logger = LoggerFactory.getLogger(SynchronizationService.class);

	private final SimpMessagingTemplate simpMessagingTemplate;

	public SynchronizationService(SimpMessagingTemplate simpMessagingTemplate) {
		super();
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@Override
	public void send(SynchronizationDto synchronizationDto) {
		logger.info(synchronizationDto.toString());
		
		if (synchronizationDto.channel == null) {
			synchronizationDto.channel = DEFAULT_CHANNEL;
		} else {
			synchronizationDto.channel = SPECYFIC_CHANNEL_PREFIX + synchronizationDto.channel;
		}

		simpMessagingTemplate.convertAndSend(synchronizationDto.channel, synchronizationDto.content);
	}

}
