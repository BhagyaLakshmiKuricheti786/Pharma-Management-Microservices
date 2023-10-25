package com.ctel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctel.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{

}
