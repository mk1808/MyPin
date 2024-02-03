package com.mypin.pinLists.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class PinList {
	
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	
	@NotBlank
	private UUID mapId;
	
	@NotBlank
	private String name;
	
	private String description;
	
	private String style;
	
	@NotBlank
	private Long orderNo;

}
