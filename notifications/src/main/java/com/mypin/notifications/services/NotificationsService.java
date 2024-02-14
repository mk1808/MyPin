package com.mypin.notifications.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mypin.notifications.clients.ISynchronizationFeignClient;
import com.mypin.notifications.dtos.NotificationDto;
import com.mypin.notifications.dtos.SynchronizationDto;
import com.mypin.notifications.exceptions.ForbiddenException;
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
		notification.setOwnerId(getCurrentUser());
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
			throw new ResourceNotFoundException("Notification with id not found");
		});
	}

	@Override
	public void delete(UUID id) {
	}

	@Override
	public void confirm(UUID id) {
		Notification notification = get(id);
		if (!hasPermissionsToReceive(notification)) {
			throw new ForbiddenException("User does not have rights to notification");
		}
		
		notification.setConfirmed(true);
		notificationRepository.save(notification);
	}

	@Override
	public List<Notification> getUserNotifications() {
		return notificationRepository.findByOwnerIdOrderByConfirmedAscUpdatedDateDesc(getCurrentUser());
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

	private UUID getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		String uuid = context.getAuthentication().getName();

		System.out.println("name: " + uuid);
		return UUID.fromString(uuid);
	}
	
	private boolean hasPermissionsToReceive(Notification notification) {
		UUID user = getCurrentUser();
		return notification.getUserId().equals(user);
	}


}
