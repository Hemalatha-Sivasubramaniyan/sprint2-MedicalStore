package com.cg.medicalstore.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/*
 * This Exception takes place when the OrderIddentifier is not available in the database
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderIdentifierException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;

	public OrderIdentifierException() {
		super();
	}

	public OrderIdentifierException(String errorMsg) {
		super(errorMsg);
	}

	

}
