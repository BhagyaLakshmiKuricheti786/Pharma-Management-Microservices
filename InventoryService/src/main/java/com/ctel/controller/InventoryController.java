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

import com.ctel.dto.OrderProductDto;
import com.ctel.entity.Inventory;
import com.ctel.service.InventoryService;

@RestController
public class InventoryController {

	@Autowired
	InventoryService inventservice;

	@PostMapping("/saveInvent")
	public ResponseEntity<?> inventValidator(@RequestBody Inventory inventory) {

		System.out.println(">>>>>>>>>>>>>>>>");
		ResponseEntity<?> response = inventservice.inventValidator(inventory);
		if (response != null)
			return ResponseEntity.ok("saved successfully");
		else
			return ResponseEntity.ok("no data present in this inventory");
	}

	@GetMapping("viewInventoryItems/{iid}")
	public ResponseEntity<?> viewInventItems(@PathVariable Integer iid) {
		Inventory invent = inventservice.viewInventoryItems(iid);
		if (invent != null)
			return ResponseEntity.ok(invent);
		else
			return ResponseEntity.ok("No items in this inventory");
	}

	@GetMapping("viewInventoryItemsByPid/{pid}")
	public ResponseEntity<?> viewInventoryItemsByPid(@PathVariable Integer pid) {
		Inventory invent = inventservice.viewInventoryItemsByPid(pid);
		if (invent != null)
			return ResponseEntity.ok(invent);
		else
			return ResponseEntity.ok("No items in this inventory");
	}

	@GetMapping("viewAllInventoryItems")
	public ResponseEntity<?> viewAllInventItems() {
		List<Inventory> invent = inventservice.viewInventoryItems();
		if (invent != null)
			return ResponseEntity.ok(invent);
		else
			return ResponseEntity.ok("No items in this inventory");
	}

	@PostMapping("updateInventoryItems/{iid}")
	public ResponseEntity<?> updateInventItems(@RequestBody Inventory inventory, @PathVariable Integer iid) {

		Inventory invent = inventservice.updateInventoryItems(inventory, iid);
		if (invent != null)
			return ResponseEntity.ok("Items updated successfully " + invent);
		else
			return ResponseEntity.ok("please enter correct details to update");
	}
	
	@PostMapping("increseQty")
	public ResponseEntity<?> increseQty(@RequestBody List<OrderProductDto> prodList) {
		
		String invent = inventservice.increseQty(prodList);
		if(invent != null)
			return ResponseEntity.ok().body("Qty incresed successfully " +invent);
		else
			return ResponseEntity.ok().body("qty is not increased, please check once");
	}

}
