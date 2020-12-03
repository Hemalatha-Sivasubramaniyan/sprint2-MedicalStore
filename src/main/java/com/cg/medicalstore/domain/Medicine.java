package com.cg.medicalstore.domain;

/**
 * The Medicine class contains necessary fields to store the medicine details.
 * @author Hemalatha S
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Medicine {

	/*
	 * Medicine class fields are defined and validation is done
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "MedicineName is required")
	@Column(unique = true, updatable = false)
	private String medicineName;
	@NotBlank(message = "Price is required")
	private double price;
	@NotBlank(message = "ExpiryDate is required")
	private String expirydate;
	@NotBlank(message = "CompanyName is required")
	private String companyName;
	@NotBlank(message = "Stock is required")
	private int stock;

	/*
	 * Default constructor of medicine class
	 */
	public Medicine() {
		super();
	}

	/*
	 * Getters and Setters of Medicine class fields
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	/*
	 * Overriding the toString method to view the medicine object
	 */
	@Override
	public String toString() {
		return "Medicine [id=" + id + ", medicineName=" + medicineName + ", price=" + price + ", expirydate="
				+ expirydate + ", companyName=" + companyName + ", stock=" + stock + "]";
	}

}
