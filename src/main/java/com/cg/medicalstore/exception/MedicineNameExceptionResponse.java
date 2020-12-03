package com.cg.medicalstore.exception;

/*
 * This exception class take place when the medicineName exception occurs.
 */
public class MedicineNameExceptionResponse  {
	private String medicineName;

	public MedicineNameExceptionResponse(String medicineName) {
		super();
		this.medicineName = medicineName;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

}
