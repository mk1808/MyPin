package com.mypin.pinLists.dtos;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.NotBlank;

public class MapDto {
	
	public UUID id;

	@NotBlank
	public String title;

	@NotBlank
	public UUID ownerId;

	@CreationTimestamp
	public Date createdDate;

	@UpdateTimestamp
	public Date updatedDate;

	@Override
	public String toString() {
		return "MapDto [id=" + id + ", title=" + title + ", ownerId=" + ownerId + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}
	
	

}
