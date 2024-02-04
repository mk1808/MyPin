package com.mypin.maps.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4650953311998188164L;
	private static final String DEFAULT_MESSAGE = "Not found";

	public ResourceNotFoundException() {
		super(DEFAULT_MESSAGE);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
