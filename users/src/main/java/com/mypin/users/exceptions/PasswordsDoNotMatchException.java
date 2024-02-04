package com.mypin.users.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class PasswordsDoNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 2817815125283258363L;
	private static final String DEFAULT_MESSAGE = "Paswords do not match";

	public PasswordsDoNotMatchException() {
		super(DEFAULT_MESSAGE);
	}

	public PasswordsDoNotMatchException(String message) {
		super(message);
	}
}
