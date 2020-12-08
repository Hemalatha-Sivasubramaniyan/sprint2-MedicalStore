package com.cg.medicalstore.service;

/*
 * MedicineService class contains the operations to be performed in medicine table
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.medicalstore.domain.Medicine;
import com.cg.medicalstore.exception.MedicineNameException;
import com.cg.medicalstore.repository.MedicineRepository;


@Service
public class MedicineService {
	@Autowired
	private MedicineRepository medicineRepository;

	/*
	 * Saving the medicine details in database
	 */
	public Medicine saveMedicine(Medicine medicine) {  

		try {
			medicine.setMedicineName(medicine.getMedicineName());
			return medicineRepository.save(medicine);
		} catch (Exception e) {
			throw new MedicineNameException("MedicineName" + medicine.getMedicineName() + " already available");
		}
	}

	/*
	 * In this method medicine price is updated by passing new medicine price
	 */
	public Medicine updatePrice(String medicineName, double price) {
		Medicine medicine = medicineRepository.findByMedicineName(medicineName);
		if (medicine == null) {
			throw new MedicineNameException(" MedicineName " + medicineName + " is not available");
		}
		medicine.setPrice(price);
		return medicineRepository.save(medicine);
	}

	/*
	 * In this method medicine ExpiryDate is updated by passing new medicine
	 * ExpiryDate
	 */
	public Medicine updateExpiryDate(String medicineName, String expiryDate) {
		Medicine medicine = medicineRepository.findByMedicineName(medicineName);
		if (medicine == null) {
			throw new MedicineNameException(" MedicineName " + medicineName + " is not available");
		}
		medicine.setExpiryDate(expiryDate);
		return medicineRepository.save(medicine);
	}

	/*
	 * In this method medicine stock is updated by passing new medicine stock
	 */
	public Medicine updateStock(String medicineName, int stock) {
		Medicine medicine = medicineRepository.findByMedicineName(medicineName);
		if (medicine == null) {
			throw new MedicineNameException(" MedicineName " + medicineName + " is not available");
		}
		medicine.setStock(stock);
		return medicineRepository.save(medicine);
	}

	/*
	 * Finding medicine details by medicine name
	 */
	public Medicine findMedicineByMedicineName(String medicineName) {
		Medicine medicine = medicineRepository.findByMedicineName(medicineName);
		if (medicine == null) {
			throw new MedicineNameException("MedicineName " + medicineName + " not available");
		}
		return medicine;
	}
	


	/*
	 * Deleting medicine details by medicine name
	 */	
	public boolean deleteMedicineByMedicineName(String medicineName) {
		Medicine medicine = findMedicineByMedicineName(medicineName);
		if (medicine == null) {
			throw new MedicineNameException("MedicineName " + medicineName + " not available");
		}
		medicineRepository.delete(medicine);
		return true;
	}

}
