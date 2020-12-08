package com.cg.medicalstore.web;

/*
 * MedicineController class contains the operations to be performed in the medicine table  
 */
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

	/*
	 * MedicineService and MapValidationErrorService objects are instantiated with
	 * the help of @Autowired
	 */
	@Autowired
	private MedicineService medicineService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/*
	 * Posting the Medicine data and checking the HTTP status if medicine data is
	 * stored in database
	 */

	@PostMapping("")
	public ResponseEntity<?> createNewMedicine(@Valid @RequestBody Medicine medicine, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		Medicine newMedicine = medicineService.saveMedicine(medicine);
		return new ResponseEntity<Medicine>(newMedicine, HttpStatus.CREATED);
	}

	/*
	 * Update price in Medicine table and checking the HTTP status if price is
	 * updated in database
	 */
	@GetMapping("/updatePrice/{medicineName}/{price}")
	public ResponseEntity<?> updateByPrice(@PathVariable String medicineName, @PathVariable double price) {
		Medicine updationOfPrice = medicineService.updatePrice(medicineName, price);
		return new ResponseEntity<Medicine>(updationOfPrice, HttpStatus.CREATED);
	}

	/*
	 * Update expiryDate in Medicine table and checking the HTTP status if expirDate
	 * is updated in database
	 */
	@GetMapping("/updateExpiryDate/{medicineName}/{expiryDate}")
	public ResponseEntity<?> updateByExpiryDate(@PathVariable String medicineName, @PathVariable String expiryDate) {
		Medicine updationOfExpiryDate = medicineService.updateExpiryDate(medicineName, expiryDate);
		return new ResponseEntity<Medicine>(updationOfExpiryDate, HttpStatus.CREATED);
	}

	/*
	 * Update Stock in the Medicine table and checking the HTTP status if stock is
	 * updated in database
	 */
	@GetMapping("/updateStock/{medicineName}/{stock}")
	public ResponseEntity<?> updateByStock(@PathVariable String medicineName, @PathVariable int stock) {
		Medicine updationOfStock = medicineService.updateStock(medicineName, stock);
		return new ResponseEntity<Medicine>(updationOfStock, HttpStatus.CREATED);
	}

	/*
	 * Get medicine details by giving medicineName
	 */
	@GetMapping("/{medicineName}")
	public ResponseEntity<?> getMedicineByMedicineName(@PathVariable String medicineName) {
		return new ResponseEntity<Medicine>(medicineService.findMedicineByMedicineName(medicineName), HttpStatus.OK);
	}

	/*
	 * Delete medicine details by giving medicineName
	 */
	@DeleteMapping("/{medicineName}")
	public ResponseEntity<?> deleteMedicine(@PathVariable String medicineName) {
		medicineService.deleteMedicineByMedicineName(medicineName);
		return new ResponseEntity<String>("Project with Id : " + medicineName.toUpperCase() + " Deleted!",
				HttpStatus.OK);
	}

}
