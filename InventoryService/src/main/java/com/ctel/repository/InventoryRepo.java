package com.ctel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ctel.entity.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer>{

	@Query(value = "SELECT * FROM inventory_table i WHERE i.prod_id = ?1", nativeQuery = true)
	Optional<Inventory> findByPid(Integer pid);

}
