package com.ctel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctel.entity.Product;
import com.ctel.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService prodservice;
	
	@PostMapping("saveProduct")
	public ResponseEntity<?> saveProduct(@RequestBody Product prod)
	{
		Product product = prodservice.saveProduct(prod);
		if(product != null)
			return ResponseEntity.ok().body("Product details saved successfully : "  +product);
		else
			return ResponseEntity.ok().body("enter the product delaitls");
	}

	
	
	@GetMapping("viewAllProducts")
	public ResponseEntity<?> getAllProducts()
	{
		List<Product> prod = prodservice.getAllProducts();
		if(prod != null)
			return ResponseEntity.ok(prod);
		else
			return ResponseEntity.ok().body("No such products are there");
	}
	
	
	
	@GetMapping("viewUserProdDetails/{pid}")
	public ResponseEntity<?> getProduct(@PathVariable Integer pid)
	{
		Product prod = prodservice.getProduct(pid);
		if(prod != null)
			return ResponseEntity.ok(prod);
		else
			return ResponseEntity.ok("no such products are present with that id");
	}
	
	
	@GetMapping("updateProdDetails/{pid}")
	public ResponseEntity<?> getProduct(@PathVariable Integer pid, @RequestBody Product product)
	{
		Product prod = prodservice.updateProdDetails(pid, product);
		if(prod != null) {
		return ResponseEntity.ok("product details updated successfully");
	}
		else
			return ResponseEntity.ok("please update the product details");

	}

}
