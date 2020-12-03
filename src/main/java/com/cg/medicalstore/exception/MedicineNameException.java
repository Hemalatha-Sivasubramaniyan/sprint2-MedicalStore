package com.cg.medicalstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * This exception takes place when the MedicineName is not found in the database 
 * to perform the particular task which extends the Runtime Exception
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MedicineNameException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MedicineNameException() {
		super();
	}

	public MedicineNameException(String errMsg) {
		super(errMsg);
	}

}
