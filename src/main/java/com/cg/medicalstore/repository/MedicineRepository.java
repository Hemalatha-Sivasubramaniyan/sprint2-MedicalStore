package com.cg.medicalstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.medicalstore.domain.Medicine;

/*
 * The medicine repository interface  extends the crud repository 
 * to implement the crud operations in the database 
 */
@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Long> {

	Medicine findByMedicineName(String medicineName);
	

}
