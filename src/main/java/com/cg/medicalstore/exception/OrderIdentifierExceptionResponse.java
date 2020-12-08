package com.cg.medicalstore.exception;

/*
 * This exception class take place when the orderIdentifier exception occurs.
 */
public class OrderIdentifierExceptionResponse {
	private String orderIdentifier;

	public OrderIdentifierExceptionResponse(String orderIdentifier) {
		super();
		this.orderIdentifier = orderIdentifier;
	}

	public String getProjectIdentifier() {
		return orderIdentifier;
	}

	public void setProjectIdentifier(String orderIdentifier) {
		this.orderIdentifier = orderIdentifier;
	}

}
