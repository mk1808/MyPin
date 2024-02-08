package com.mypin.synchronization.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.mypin.synchronization.dtos.SynchronizationDto;

@Service
public interface ISynchronizationService {

	void send(SynchronizationDto synchronizationDto);
}
