package com.cg.medicalstore.service;
/*
 * The OrderService contains methods to be performed in the Order table
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.medicalstore.domain.Order;
import com.cg.medicalstore.exception.OrderIdentifierException;
import com.cg.medicalstore.repository.OrderRepository;

@Service
public class OrderService {

	/*
	 * OrderRepository object is instantiated using @Autowired
	 */
	@Autowired
	private OrderRepository orderRepository;

	/*
	 * Method to save the order in database
	 */
	public Order saveOrder(Order order) {
		try {
			order.setOrderIdentifier(order.getOrderIdentifier());
			return orderRepository.save(order);
		} catch (Exception e) {
			throw new OrderIdentifierException("OrderIdentifier " + order.getOrderIdentifier() + " already available");
		}
	}

	/*
	 * Method to view the order history by orderIdentifier
	 */
	public Order findOrderByOrderIdentifier(String orderIdentifier) {
		Order order = orderRepository.findByOrderIdentifier(orderIdentifier);
		if (order == null) {
			throw new OrderIdentifierException("OrderIdentifier " + orderIdentifier + " not available");
		}
		return order;
	}

	/*
	 * Method to view the Orders
	 */
	public Iterable<Order> findAllOrders() {
		return orderRepository.findAll();
	}

	/*
	 * Method to update delivery status if delivery is done
	 */
	public Order updateDeliveryStatus(String orderIdentifier, String deliveryStatus) {
		Order order = orderRepository.findByOrderIdentifier(orderIdentifier);
		if (order == null) {
			throw new OrderIdentifierException("OrderIdentifier " + orderIdentifier + " not available ");
		}
		order.setDeliveryStatus(deliveryStatus);
		return orderRepository.save(order);
	}

}
