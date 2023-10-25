package com.ctel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctel.entity.Customer;
import com.ctel.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService custservice;
	
	@PostMapping("customerRegister")
	public ResponseEntity<?> custRegister(@RequestBody Customer cust)
	{
		ResponseEntity response = custservice.custRegister(cust);
		return ResponseEntity.ok("User Registered successfully" +response);
	}
	
	@PutMapping("updateDetails/{cid}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Integer cid) {

    
    	Customer cust = custservice.updateCustomer(cid, customer);
    	if(cust != null)
    	return ResponseEntity.ok().body("Updated successfully" +cust);
    	else
        	return ResponseEntity.ok().body("you are not logined, please login and update ur details");
    }
	
	@GetMapping("viewCustDetails/{cid}")
	public ResponseEntity<?> viewCustDetails(@PathVariable Integer cid)
	{
		Customer cust = custservice.viewCustDetails(cid);
		if(cust != null)
			return ResponseEntity.ok(cust);
		else
			return ResponseEntity.ok("No such customer with this id");
	}
	
	@GetMapping("viewAllCusts")
	public ResponseEntity<?> viewAllCusts() {
	List<Customer> cust = custservice.viewAllCusts();
	if(cust != null)
	return ResponseEntity.ok(cust);
	else
		return ResponseEntity.ok("no such customers are there");
	}
		
	@DeleteMapping("deleteCust/{cid}")
	public ResponseEntity<?> deleteCust(@PathVariable Integer cid)
	{
		Customer customer = custservice.deleteCust(cid);
		if(customer != null)
			return ResponseEntity.ok("customer deleted successfully");
		else
			return ResponseEntity.ok("No such customer present with that id");
	}
}
