package com.mypin.notifications.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mypin.notifications.dtos.NotificationDto;
import com.mypin.notifications.models.Notification;

@Service
public interface INotificationsService extends ICrudService<Notification> {
	void confirm(UUID id);

	List<Notification> getUserNotifications();

	Notification save(NotificationDto notificationDto);
}
