package com.mypin.maps.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Sharing {
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	@NotNull
	private UUID mapId;
	
	@NotNull
	private UUID userId;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getMapId() {
		return mapId;
	}
	public void setMapId(UUID mapId) {
		this.mapId = mapId;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
	
}
