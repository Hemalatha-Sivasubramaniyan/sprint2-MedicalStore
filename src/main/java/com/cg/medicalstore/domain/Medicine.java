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
import javax.validation.constraints.NotNull;

@Entity
public class Medicine {

	/*
	 * Medicine class fields are defined and validation is done
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "MedicineName is required")
	@Column(unique = true)
	private String medicineName;
	@NotNull(message = "Price is required")
	private double price;
	@NotBlank(message = "ExpiryDate is required")
	private String expiryDate;
	@NotBlank(message = "CompanyName is required")
	private String companyName;
	@NotNull(message = "Stock is required")
	private int stock;

	/*
	 * Default constructor of medicine class
	 */
	public Medicine() {
		super();
	}

	
    /*
     * Parameterized constructor for medicine class
     */
	public Medicine( String medicineName,double price,String expiryDate,String companyName,int stock) {
		super();
		this.medicineName = medicineName;
		this.price = price;
		this.expiryDate = expiryDate;
		this.companyName = companyName;
		this.stock = stock;
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

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
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
				+ expiryDate + ", companyName=" + companyName + ", stock=" + stock + "]";
	}

}
