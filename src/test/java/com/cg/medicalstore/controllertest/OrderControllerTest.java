package com.cg.medicalstore.controllertest;
/*
 * This is the OrderControllerTest which contains test for OrderController
 */

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.cg.medicalstore.domain.Order;
import com.cg.medicalstore.service.MapValidationErrorService;
import com.cg.medicalstore.service.OrderService;
import com.cg.medicalstore.web.OrderController;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	OrderService  orderService;
	
	@MockBean
	MapValidationErrorService mapValidationErrorService;
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	/*
	 * Test for saveOrder in OrderController
	 */
	@Test
	public void testSaveOrder() throws Exception  {
		Order order = new Order("101","Manu","Maya",36,"deliveryNotDone",Arrays.asList("Ascazine","Folvite","Supradyne"));
		Mockito.when(orderService.saveOrder(ArgumentMatchers.any())).thenReturn(order);
		String json = mapper.writeValueAsString(order);
		mockMvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$").isMap()) 
		.andExpect(jsonPath("orderIdentifier").value("101"))
		.andExpect(jsonPath("doctorName").value("Manu"))
		.andExpect(jsonPath("patientName").value("Maya"))
		.andExpect(jsonPath("patientAge").value(36))
		.andExpect(jsonPath("deliveryStatus").value("deliveryNotDone"));
		
	}
	
	/*
	 * Test for viewOrderHistory in OrderController
	 */
	@Test
	public void testViewOrderHistoryById() throws Exception {
		Order order = new Order("101","Manu","Maya",36,"deliveryNotDone",Arrays.asList("Ascazine","Folvite","Supradyne"));
		Mockito.when(orderService.findOrderByOrderIdentifier("101")).thenReturn(order);
		mockMvc.perform(get("/api/orders/101"))
		          .andExpect(status().isOk())
		          .andExpect(jsonPath("orderIdentifier", Matchers.equalTo("101")));
	}
	
	/*
	 * Test for viewOrders in OrderController
	 */
	@Test
	public void testViewOrders() throws Exception {
		List<Order> list = new ArrayList<>();
		list.add(new Order("101","Surya","Madhavi",24,"deliverNotDone",Arrays.asList("Evion-400","Folvite","Supradyne")));
		list.add(new Order("102","Madhi","Renu",37,"deliveryDone",Arrays.asList("Zincovit","Alkof","dolo")));
		Mockito.when(orderService.findAllOrders()).thenReturn(list);
	    mockMvc.perform(get("/api/orders/all"))
	            .andExpect(status().isOk())
				.andExpect(jsonPath("$",Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].orderIdentifier",Matchers.equalTo("101")));
		
	}
	
	/*
	 * Test for deliverOrder in OrderController
	 */
	@Test
	public void testDeliverOrder() throws Exception {
		Order order = new Order("100","Divya","Madhu",21,"deliveryNotDone",Arrays.asList("keto","Cipladine"));
		Mockito.when(orderService.updateDeliveryStatus("100", "deliveryDone")).thenReturn(order);
		order.setDeliveryStatus("deliveryDone");
		String json = mapper.writeValueAsString(order);
		mockMvc.perform(get("/api/orders/deliveryStatus/100/deliveryDone").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isCreated())
		        .andExpect(jsonPath("orderIdentifier").value("100"));	
	}
	
	

}
