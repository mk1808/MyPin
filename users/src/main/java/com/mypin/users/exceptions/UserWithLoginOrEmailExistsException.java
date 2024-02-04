package com.mypin.users.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UserWithLoginOrEmailExistsException extends RuntimeException {

	private static final long serialVersionUID = 2817815125283258363L;
	private static final String DEFAULT_MESSAGE = "User with login or email exists";

	public UserWithLoginOrEmailExistsException() {
		super(DEFAULT_MESSAGE);
	}

	public UserWithLoginOrEmailExistsException(String message) {
		super(message);
	}
}
