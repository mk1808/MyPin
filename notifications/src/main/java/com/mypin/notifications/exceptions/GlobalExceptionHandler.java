package com.mypin.notifications.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mypin.notifications.dtos.ErrorDto;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<ErrorDto> handleNotFound(RuntimeException ex, WebRequest request) {
		ErrorDto e = new ErrorDto();
		e.message = ex.getMessage();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	}
	
	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	protected ResponseEntity<ErrorDto> handleBadRequest(RuntimeException ex, WebRequest request) {
		ErrorDto e = new ErrorDto();
		e.message = ex.getMessage();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	}
}