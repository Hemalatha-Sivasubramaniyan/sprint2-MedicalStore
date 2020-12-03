package com.cg.medicalstore.service;

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
     * Saving or updating the medicine details
     */
	public Medicine saveOrUpdate(Medicine medicine) {
		try {
			medicine.setMedicineName(medicine.getMedicineName().toUpperCase());
			return medicineRepository.save(medicine);
		}
		catch (Exception e) {
			throw new MedicineNameException("MedicineName "+medicine.getMedicineName()+" already available");
		}
	}
	
	/*
	 * Finding medicine details by medicine name
	 */
	public Medicine findMedicineByMedicineName(String medicineName) {
		Medicine medicine = medicineRepository.findByMedicineName(medicineName.toUpperCase());
		if (medicine == null) {
			throw new MedicineNameException("MedicineName " + medicineName + " not available");
		}
		return medicine;
	}
	
	/*
	 * Deleting medicine details by medicine name
	 */
	public void deleteMedicineByMedicineName(String medicineName) {
		Medicine medicine=findMedicineByMedicineName(medicineName.toUpperCase());
		if(medicine==null) {
			throw new MedicineNameException("MedicineName " + medicineName + " not available");
		}
		medicineRepository.delete(medicine);
	}

}
