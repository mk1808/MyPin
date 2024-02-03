package com.mypin.pinLists.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pin {
	
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	
	@NotBlank
	private UUID pinListId;
	
	@NotBlank
	private String name;
	
	private String description;
	
	@NotBlank
	private Long orderNo;
	
	@NotBlank
	private String longitude;
	
	@NotBlank
	private String latitude;
	
	private String openStreetMapId;

}
