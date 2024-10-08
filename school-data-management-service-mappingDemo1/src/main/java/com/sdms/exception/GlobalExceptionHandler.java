package com.sdms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SDMSException.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		return new ResponseEntity(ErrorResponse.builder().flag(false).message(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
