package com.cg.medicalstore.web;
/*
 * The OrderService contains methods to be performed in the Order table
 */

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.medicalstore.domain.Order;
import com.cg.medicalstore.service.MapValidationErrorService;
import com.cg.medicalstore.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	/*
	 * OrderService and MapValidationErrorService objects are instantiated with the
	 * help of @Autowired
	 */
	@Autowired
	private OrderService orderService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/*
	 * Order data are stored in the database
	 */
	@PostMapping("")
	public ResponseEntity<?> createNewOrder(@Valid @RequestBody Order order, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		Order newOrder = orderService.saveOrder(order);
		return new ResponseEntity<Order>(newOrder, HttpStatus.CREATED);

	}

	/*
	 * By giving the orderIdentifier we can see the orderHistory of particular order
	 */
	@GetMapping("/{orderIdentifier}")
	public ResponseEntity<?> viewOrderHistoryById(@PathVariable String orderIdentifier) {
		return new ResponseEntity<Order>(orderService.findOrderByOrderIdentifier(orderIdentifier), HttpStatus.OK);
	}

	/*
	 * Number of orders will be viewed by this method
	 */
	@GetMapping("/all")
	public Iterable<Order> viewOrders() {
		return orderService.findAllOrders();
	}

	/*
	 * DeliveryStatus is updated if delivery is done
	 */
	@GetMapping("/deliveryStatus/{orderIdentifier}/{deliveryStatus}")
	public ResponseEntity<?> deliverOrder(@PathVariable String orderIdentifier, @PathVariable String deliveryStatus) {
		Order orderStatus = orderService.updateDeliveryStatus(orderIdentifier, deliveryStatus);
		return new ResponseEntity<Order>(orderStatus, HttpStatus.CREATED);
	}

}
