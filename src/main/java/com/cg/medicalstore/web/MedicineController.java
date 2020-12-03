package com.cg.medicalstore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.medicalstore.domain.Medicine;
import com.cg.medicalstore.service.MapValidationErrorService;
import com.cg.medicalstore.service.MedicineService;

@RestController
@RequestMapping("/api/medical")
public class MedicineController {
	
		@Autowired
		private MedicineService medicineService;
		
		@Autowired
		private MapValidationErrorService mapValidationErrorService;

		/*
		 * Posting the Medicine data
		 */
		@PostMapping("")
		public ResponseEntity<?> createNewMedicine(@Valid @RequestBody Medicine medicine, BindingResult result) {
			ResponseEntity<?> errorMap =  mapValidationErrorService.mapValidationError(result);
			if(errorMap!=null) return errorMap;
			Medicine newMedicine = medicineService.saveOrUpdate(medicine);
			return new ResponseEntity<Medicine>(newMedicine, HttpStatus.CREATED);
		}

		/*
		 * Get medicine details by giving medicineName
		 */
		@GetMapping("/{medicineName}")
		public ResponseEntity<?> getMedicineByMedicineName(@PathVariable String medicineName){
			return new ResponseEntity<Medicine>( medicineService.findMedicineByMedicineName(medicineName),HttpStatus.OK);
		}
		
		/*
		 * Delete medicine details by medicineName
		 */
		@DeleteMapping("/{medicineName}")
		public ResponseEntity<?> deleteMedicine(@PathVariable String medicineName){
			medicineService.deleteMedicineByMedicineName(medicineName);
			return new ResponseEntity<String> ("Project with Id : "+medicineName.toUpperCase()+" Deleted!",HttpStatus.OK);
		}
}
