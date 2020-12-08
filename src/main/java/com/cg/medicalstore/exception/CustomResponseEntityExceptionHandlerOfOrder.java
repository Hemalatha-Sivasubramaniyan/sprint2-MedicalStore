package com.cg.medicalstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * This customResponseEntityExceptionHandller will handle the exception occurs in the responseEntity of Order class
 */

public class CustomResponseEntityExceptionHandlerOfOrder extends ResponseEntityExceptionHandler {
	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectIDException(OrderIdentifierException ex, WebRequest request){
		OrderIdentifierExceptionResponse exceptionResponse=new OrderIdentifierExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}

}
