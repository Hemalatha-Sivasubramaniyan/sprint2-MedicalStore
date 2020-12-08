package com.cg.medicalstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.medicalstore.domain.Order;

/*
 * The Order repository interface  extends the crud repository 
 * to implement the crud operations in the database 
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
	Order findByOrderIdentifier(String orderIdentifier);
}
