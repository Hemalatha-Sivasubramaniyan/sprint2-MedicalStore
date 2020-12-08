package com.cg.medicalstore.domain;

/**
 * The Order class contains necessary fields to store Order class data
 * @author HEMALATHA S
 */

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "OrderMedicine")
public class Order {

	/*
	 * Attributes of Order class
	 */
	@Id
	@NotBlank(message = "OrderIdentifier is required")
	@Size(min = 3, max = 4, message = "Size must be between 3 to 4 characters")
	@Column(unique = true, updatable = false)
	private String orderIdentifier;
	@NotBlank(message = "DoctorName is required")
	private String doctorName;
	@NotBlank(message = "PatientName is required")
	private String patientName;
	@NotNull(message = "PatientAge is required")
	private int patientAge;
	private String deliveryStatus;
	@ElementCollection(targetClass = String.class)
	private List<String> medicineList;

	/*
	 * Default Constructor of Order class
	 */
	public Order() {
		super();
	}

	/*
	 * Parameterized Constructor of Order class
	 */
	public Order(String orderIdentifier, String doctorName, String patientName, int patientAge, String deliveryStatus,
			List<String> medicineList) {
		super();
		this.orderIdentifier = orderIdentifier;
		this.doctorName = doctorName;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.deliveryStatus = deliveryStatus;
		this.medicineList = medicineList;
	}

	/*
	 * Getters and Setters for Order class
	 */
	public String getOrderIdentifier() {
		return orderIdentifier;
	}

	public void setOrderIdentifier(String orderIdentifier) {
		this.orderIdentifier = orderIdentifier;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public List<String> getMedicineList() {
		return medicineList;
	}

	public void setMedicineList(List<String> medicineList) {
		this.medicineList = medicineList;
	}

	/*
	 * toString method of Order class
	 */
	@Override
	public String toString() {
		return "Order [ orderIdentifier=" + orderIdentifier + ", doctorName=" + doctorName + ", patientName="
				+ patientName + ", patientAge=" + patientAge + ", deliveryStatus=" + deliveryStatus + ", medicineList="
				+ medicineList + "]";
	}

}
