package com.cg.medicalstore.controllertest;

/*
 * MedicineControllerTest class contains test methods for medicine Controller
 */
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.medicalstore.domain.Medicine;
import com.cg.medicalstore.service.MapValidationErrorService;
import com.cg.medicalstore.service.MedicineService;
import com.cg.medicalstore.web.MedicineController;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MedicineController.class)
public class MedicineControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	MedicineService medicineService;

	@MockBean
	MapValidationErrorService mapValidationErrorService;

	private static ObjectMapper mapper = new ObjectMapper();

	/*
	 * Test for save method in medicine controller where the HTTP status is checked
	 * with actual and expected value
	 */
	@Test
	public void testSaveMedicine() throws Exception {
		Medicine medicine = new Medicine("Zincovit", 18.09d, "01/23", "gsk", 100);
		Mockito.when(medicineService.saveMedicine(ArgumentMatchers.any())).thenReturn(medicine);
		String json = mapper.writeValueAsString(medicine);
		mockMvc.perform(post("/api/medical").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(status().isCreated()).andExpect(jsonPath("$").isMap())
				.andExpect(jsonPath("medicineName").value("Zincovit")).andExpect(jsonPath("price").value(18.09d))
				.andExpect(jsonPath("expiryDate").value("01/23")).andExpect(jsonPath("companyName").value("gsk"))
				.andExpect(jsonPath("stock").value(100));
	}

	/*
	 * Test for findByMedicineName method in medicine controller where the HTTP
	 * status is checked with actual and expected value
	 */
	@Test
	public void testFindByMedicineName() throws Exception {
		Medicine medicine = new Medicine("telvas", 112.56d, "07/22", "sunPharma", 50);
		Mockito.when(medicineService.findMedicineByMedicineName("telvas")).thenReturn(medicine);
		mockMvc.perform(get("/api/medical/telvas")).andExpect(status().isOk())
				.andExpect(jsonPath("medicineName", Matchers.equalTo("telvas")));
	}

	/*
	 * Test for method in medicine controller where the HTTP status is checked with
	 * actual and expected value
	 */
	@Test
	public void testDeleteMedicineByMedicineName() throws Exception {
		Medicine newMedicine = new Medicine("Betnovate", 23.78d, "07/21", "gsk", 10);
		Mockito.when(medicineService.deleteMedicineByMedicineName("Betnovate")).thenReturn(true);
		mockMvc.perform(delete("/api/medical/Betnovate")).andExpect(status().isOk()).andExpect(jsonPath("$",
				Matchers.anything("Deleted Medicine details with Name: " + newMedicine.getMedicineName() + "!")));
	}

	/*
	 * Test for updateExpiryDate method in medicine controller where the HTTP status
	 * is checked with actual and expected value
	 */
	@Test
	public void testUpdateByExpiryDate() throws Exception {
		Medicine medicine = new Medicine("Okacet", 18.09d, "09/22", "Cipla", 100);
		Mockito.when(medicineService.updateExpiryDate("Okacet", "05-21")).thenReturn(medicine);
		medicine.setExpiryDate("05-21");
		String json = mapper.writeValueAsString(medicine);
		mockMvc.perform(get("/api/medical/updateExpiryDate/Okacet/05-21").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("expiryDate").value("05-21"));
	}

	/*
	 * Test for updateByPrice method in medicine controller where the HTTP status is
	 * checked with actual and expected value
	 */
	@Test
	public void testUpdateByPrice() throws Exception {
		Medicine medicine = new Medicine("Fopymin", 18.09d, "10/22", "Cipla", 200);
		Mockito.when(medicineService.updatePrice("Fopymin", 25.98d)).thenReturn(medicine);
		medicine.setPrice(25.98d);
		String json = mapper.writeValueAsString(medicine);
		mockMvc.perform(get("/api/medical/updatePrice/Fopymin/25.98d").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("price").value(25.98d));
	}

	/*
	 * Test for updateByStock method in medicine controller where the HTTP status is
	 * checked with actual and expected value
	 */
	@Test
	public void testUpdateByStock() throws Exception {
		Medicine medicineObj = new Medicine("Jostel", 45.78d, "06/22", "Cipla", 40);
		Mockito.when(medicineService.updateStock("Jostel", 20)).thenReturn(medicineObj);
		medicineObj.setStock(20);
		String json = mapper.writeValueAsString(medicineObj);
		mockMvc.perform(get("/api/medical/updateStock/Jostel/20").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("stock").value(20));
	}

}
