package com.mypin.notifications.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mypin.notifications.models.Notification;
import com.mypin.notifications.repositories.INotificationsRepository;

@Service
public class NotificationsService implements INotificationsService {

	private final INotificationsRepository notificationRepository;
	
	public NotificationsService(INotificationsRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	@Override
	public Notification save(Notification obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification update(Notification obj, UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification get(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
