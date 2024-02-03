package com.mypin.maps.dtos;

import jakarta.validation.constraints.Email;

public class SharingDto {
	
	@Email
	public String email;

}
