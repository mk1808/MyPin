package com.mypin.maps.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AppUserDto {
	
	public UUID id;
	@Email
	public String email;
	@NotBlank
	public String login;

}
