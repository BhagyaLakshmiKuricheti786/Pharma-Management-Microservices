package com.ctel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctel.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
