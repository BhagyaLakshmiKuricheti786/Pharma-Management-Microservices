package com.ctel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ctel.entity.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query(value = "SELECT * FROM customer_table c WHERE c.email_id = ?1", nativeQuery = true)
	Customer findByEmailId(String emailId);

}
