package com.mypin.notifications.models;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Notification {
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	@NotBlank
	private UUID ownerId;

	@NotBlank
	private UUID userId;

	@NotBlank
	private String type;

	@NotBlank
	private String content;

	@CreationTimestamp
	private Date createdDate;

	@UpdateTimestamp
	private Date updatedDate;
}
