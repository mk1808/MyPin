package com.mypin.notifications.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mypin.notifications.clients.ISynchronizationFeignClient;
import com.mypin.notifications.dtos.NotificationDto;
import com.mypin.notifications.dtos.SynchronizationDto;
import com.mypin.notifications.exceptions.ResourceNotFoundException;
import com.mypin.notifications.models.Notification;
import com.mypin.notifications.repositories.INotificationsRepository;

@Service
public class NotificationsService implements INotificationsService {

	private final INotificationsRepository notificationRepository;
	private final ISynchronizationFeignClient synchronizationFeignClient;

	public NotificationsService(INotificationsRepository notificationRepository, ISynchronizationFeignClient synchronizationFeignClient) {
		this.notificationRepository = notificationRepository;
		this.synchronizationFeignClient = synchronizationFeignClient;
	}

	@Override
	public Notification save(Notification notification) {
		notification.setConfirmed(false);
		// TODO: get and set userId
		notificationRepository.save(notification);
		synchronizeNotification(notification);
		return notification;
	}

	@Override
	public Notification update(Notification notification, UUID id) {
		return null;
	}

	@Override
	public Notification get(UUID id) {
		return notificationRepository.findById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException("User with email not found");
		});
	}

	@Override
	public void delete(UUID id) {
		get(id);
	}

	@Override
	public void confirm(UUID id) {
		Notification notification = get(id);
		notification.setConfirmed(true);
		notificationRepository.save(notification);
	}

	@Override
	public List<Notification> getUserNotifications() {
		// TODO: get owner
		return notificationRepository.findByOwnerIdOrderByConfirmedAscUpdatedDateDesc(UUID.fromString("2c19fd33-67f2-4379-921a-3ab17ae9b05b"));
	}

	@Override
	public Notification save(NotificationDto notificationDto) {
		Notification notification = new Notification();
		notification.setOwnerId(notificationDto.ownerId);
		notification.setUserId(notificationDto.userId);
		notification.setType(notificationDto.type);
		notification.setContent(notificationDto.content);
		return save(notification);
	}
	
	private void synchronizeNotification(Notification notification) {
		SynchronizationDto synchronizationDto = new SynchronizationDto();
		synchronizationDto.channel = notification.getOwnerId().toString();
		synchronizationDto.content = notification.getContent(); // TODO fill content
		synchronizationFeignClient.sendSynchronizationMessage(synchronizationDto);
	}


}
