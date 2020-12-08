package com.cg.medicalstore.servicetest;

/*
 * MedicineServiceTest contains test method for Medicine Servic
 */
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.cg.medicalstore.domain.Medicine;
import com.cg.medicalstore.repository.MedicineRepository;
import com.cg.medicalstore.service.MedicineService;

@ExtendWith(MockitoExtension.class)
public class MedicineServiceTest {
	@Mock
	private MedicineRepository medicineRepository;

	@InjectMocks
	private MedicineService medicineService;

	/*
	 * Test for saveMedicine method in MedicineService
	 */

	@Test
	public void testSaveMedicine() {
		Medicine newMedicine = new Medicine("Okacet", 18.09d, "09/22", "Cipla", 100);
		when(medicineRepository.save(Mockito.any(Medicine.class))).thenReturn(newMedicine);
		Medicine medicineFound = medicineService.saveMedicine(newMedicine);
		assertEquals("Okacet", medicineFound.getMedicineName());
	}

	/*
	 * Test for findMedicineByMedicineName method in MedicineService
	 */
	@Test
	public void testFindMedicineByMedicineName() {
		Medicine newMedicine = new Medicine("Betnovate", 23.78d, "07/21", "gsk", 10);
		Mockito.when(medicineRepository.findByMedicineName("Betnovate")).thenReturn(newMedicine).thenReturn(null);
		Medicine result = medicineService.findMedicineByMedicineName("Betnovate");
		assertThat(result);
	}

	/*
	 * Test for deleteMedicineByMedicineName method in MedicineService
	 */
	@Test
	public void testDeleteMedicineByMedicineName() {
		Medicine medicineObj = new Medicine("Jostel", 45.78d, "06/22", "Cipla", 40);
		Mockito.when(medicineRepository.findByMedicineName("JOSTEL")).thenReturn(medicineObj).thenReturn(null);
		boolean result = medicineService.deleteMedicineByMedicineName("JOSTEL");
		assertThat(result);

	}

	/*
	 * Test for updateStock method in MedicineService
	 */
	@Test
	public void testUpdateStock() {
		Medicine medicine = new Medicine("Zincovit", 18.09d, "01/23", "gsk", 100);
		when(medicineRepository.findByMedicineName("Okacet")).thenReturn(medicine);
		when(medicineRepository.save(Mockito.any(Medicine.class))).thenReturn(medicine);
		Medicine newMedicine = medicineService.updateStock("Okacet", 45);
		assertEquals(45, newMedicine.getStock());
	}

	/*
	 * Test for updatePrice method in MedicineService
	 */
	@Test
	public void testUpdatePrice() {
		Medicine medicine = new Medicine("Fopymin", 18.09d, "10/22", "Cipla", 200);
		when(medicineRepository.findByMedicineName("Okacet")).thenReturn(medicine);
		when(medicineRepository.save(Mockito.any(Medicine.class))).thenReturn(medicine);
		Medicine newMedicine = medicineService.updatePrice("Okacet", 89.78d);
		assertEquals(89.78d, newMedicine.getPrice());
	}

	/*
	 * Test for updateExpiryDate method in MedicineService
	 */
	@Test
	public void testUpdateExpiryDate() {
		Medicine medicine = new Medicine("telvas", 112.56d, "07/22", "sunPharma", 50);
		when(medicineRepository.findByMedicineName("telvas")).thenReturn(medicine);
		when(medicineRepository.save(Mockito.any(Medicine.class))).thenReturn(medicine);
		Medicine newMedicine = medicineService.updateExpiryDate("telvas", "05/21");
		assertEquals("05/21", newMedicine.getExpiryDate());
	}

}
