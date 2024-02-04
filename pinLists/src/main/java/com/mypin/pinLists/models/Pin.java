package com.mypin.pinLists.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pin {
	
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	
	@NotNull
	private UUID pinListId;
	
	@NotBlank
	private String name;
	
	private String description;
	
	@NotNull
	private Long orderNo;
	
	@NotBlank
	private String longitude;
	
	@NotBlank
	private String latitude;
	
	private String openStreetMapId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getPinListId() {
		return pinListId;
	}

	public void setPinListId(UUID pinListId) {
		this.pinListId = pinListId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getOpenStreetMapId() {
		return openStreetMapId;
	}

	public void setOpenStreetMapId(String openStreetMapId) {
		this.openStreetMapId = openStreetMapId;
	}
}
