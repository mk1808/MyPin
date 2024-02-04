package com.mypin.maps.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypin.maps.dtos.AppUserDto;

@FeignClient("users")
public interface IUsersFeignClient {
	
	@GetMapping("/api")
	public ResponseEntity<AppUserDto> getByEmail(@RequestParam String email);

}
