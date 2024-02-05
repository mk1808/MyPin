package com.mypin.notifications.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -762290672204613059L;
	private static final String DEFAULT_MESSAGE = "Not found";

	public ResourceNotFoundException() {
		super(DEFAULT_MESSAGE);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
