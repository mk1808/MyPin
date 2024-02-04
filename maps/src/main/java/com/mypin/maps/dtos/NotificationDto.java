package com.mypin.maps.dtos;

import java.util.UUID;

import com.mypin.maps.enums.NotificationType;

public class NotificationDto {
	public UUID ownerId;
	public UUID userId;
	public NotificationType type;
	public String content;
	
	public NotificationDto() {
		super();
	}
	
	public NotificationDto(UUID ownerId,  UUID userId,  NotificationType type, String content) {
		super();
		this.ownerId = ownerId;
		this.userId = userId;
		this.type = type;
		this.content = content;
	}
	
	
}
