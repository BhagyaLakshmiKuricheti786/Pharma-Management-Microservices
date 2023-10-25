package com.ctel.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctel.entity.Product;
import com.ctel.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	ProductRepo prodrepo;

	public Product saveProduct(Product product) {
		if (product.getProdName() != null && product.getPrice() > 0) {
			product.setMfgDate(LocalDateTime.now());
			product.setExpDate(LocalDateTime.now().plusMonths(6));
		}
		Product prod = prodrepo.save(product);
		return prod;
	}

	public List<Product> getAllProducts() {
		List<Product> prod = prodrepo.findAll();
		return prod;
	}

	public Product getProduct(Integer pid) {
		Optional<Product> prod = prodrepo.findById(pid);
		if (prod.isPresent() == true) {
			return prod.get();
		} else
			return null;
	}

	public Product updateProdDetails(Integer pid, Product product) {
		Optional<Product> prod = prodrepo.findById(pid);
		if (prod.isPresent() == true) {
			Product prodct = prod.get();
			System.out.println(product);
			System.out.println(prodct);

			Product dummy=new Product();
			System.out.println(dummy.toString());
			
			if (product.getProdName() != null) {
				if (!product.getProdName().equals(prodct.getProdName()))
					prodct.setProdName(product.getProdName());
			}
			if (!product.getPrice().equals(prodct.getPrice()))
				prodct.setPrice(product.getPrice());
			
			if (product.getManufactureName() != null) {
				if (!product.getManufactureName().equals(prodct.getManufactureName()))
					prodct.setManufactureName(product.getManufactureName());
			}
			prodct.setMfgDate(LocalDateTime.now());
			prodct.setExpDate(LocalDateTime.now().plusMonths(6));
			System.out.println(prodct);
			prodrepo.save(prodct);
			return prodrepo.save(prodct);
		} else
			return null;
	}
}
