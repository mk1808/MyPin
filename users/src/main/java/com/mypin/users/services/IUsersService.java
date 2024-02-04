package com.mypin.users.services;

import org.springframework.stereotype.Service;

import com.mypin.users.dtos.RegisterDto;
import com.mypin.users.models.AppUser;

@Service
public interface IUsersService extends ICrudService<AppUser> {

	public AppUser register(RegisterDto registerDto);

	public AppUser getByEmail(String email);
}
