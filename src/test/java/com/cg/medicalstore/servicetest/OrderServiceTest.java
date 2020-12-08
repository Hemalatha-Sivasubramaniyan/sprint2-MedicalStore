package com.cg.medicalstore.servicetest;

/*
 * This is the OrderServiceTest which contains test for OrderService
 */

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.medicalstore.domain.Order;
import com.cg.medicalstore.repository.OrderRepository;
import com.cg.medicalstore.service.OrderService;
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	@Mock
	private OrderRepository orderRepository;
	
	@InjectMocks
	private OrderService orderService;
	
	/*
	 * Test for saveOrder method in OrderService
	 */
	@Test
	public void testSaveOrder() {
		Order order = new Order("100","Divya","Madhu",21,"deliveryDone",Arrays.asList("keto","Cipladine"));
		when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
		Order newOrder = orderService.saveOrder(order);
		assertEquals("100", newOrder.getOrderIdentifier());
	}
	
	/*
	 * Test for deliverOrder method in OrderService
	 */
	@Test
	public void testDeliverOrder() {
		//List<String> list=new ArrayList<>();
		Order order = new Order("100","Suman","Raju",56,"null",Arrays.asList("dolo","okacet"));
		when(orderRepository.findByOrderIdentifier("100")).thenReturn(order);
		when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
		Order newOrder = orderService.updateDeliveryStatus("100","deliveryDone");
		assertEquals("deliveryDone", newOrder.getDeliveryStatus());
	}
	
	/*
	 * Test for  findOrderByOrderIdentifier method in MedicineService
	 */
	@Test
	public void testFindOrderByOrderIdentifier() {
		Order order = new Order("101","Manu","Maya",36,"deliveryNotDone",Arrays.asList("Ascazine","Folvite","Supradyne"));
		Mockito.when(orderRepository.findByOrderIdentifier("101")).thenReturn(order).thenReturn(null);
		Order result = orderService.findOrderByOrderIdentifier("101");
		assertThat(result);
	}
	
	/*
	 * Test for findAllOrders method in MedicineService
	 */
	@Test
	public void testFindAllOrders()
	{
		List<Order> list = new LinkedList<>();
		list.add(new Order("101","Surya","Madhavi",24,"deliverNotDone",Arrays.asList("Evion-400","Folvite","Supradyne")));
		list.add(new Order("101","Madhi","Renu",37,"deliveryDone",Arrays.asList("Zincovit","Alkof","dolo")));
		when(orderRepository.findAll()).thenReturn(list);
		Iterable<Order> result = orderService.findAllOrders();
		assertEquals(list.size(), ((List<Order>) result).size());
	}

}
