package com.ctel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctel.entity.Order;
import com.ctel.service.OrderService2;

@RestController
//@RequestMapping(produces = "application/json")
public class OrderController {

	@Autowired
	OrderService2 orderservice;

	@PostMapping("orderValidator")
	public ResponseEntity<?> orderValidator(@RequestBody Order order) {
		Order ord = orderservice.orderValidator(order);
		if (ord != null)
			return ResponseEntity.ok("Order saved successfully" + ord.toString());
		else
			return ResponseEntity.ok("please enter the order details");

	}

	@DeleteMapping("deleteOrder/{oid}")
	public String deleteOrder(@PathVariable Integer oid ) {
		
		String str = orderservice.deleteOrder(oid);
		if(str != null)
			return "Order canceled successfully";
		else
			return "order is not canceled";
	}
	}


	
	

	

