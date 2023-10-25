package com.ctel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctel.entity.OrderProduct;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderProduct, Integer>{

}
