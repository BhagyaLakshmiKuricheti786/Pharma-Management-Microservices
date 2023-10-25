
package com.ctel.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ctel.dto.OrderProductDto;
import com.ctel.entity.Inventory;
import com.ctel.entity.ProductDto;
import com.ctel.repository.InventoryRepo;

@Service
public class InventoryService {

	@Autowired
	InventoryRepo inventrepo;

//	@Autowired
//	ProductRepo prodRepo;

	@Autowired
	RestTemplate resttemplate;

	public ResponseEntity<?> inventValidator(Inventory inventory) {

//			Optional<Inventory> invent = inventrepo.findById(inventory);
//			if(invent.isPresent()==true) {

		String url = "http://localhost:8092/viewUserProdDetails/";
		ResponseEntity<ProductDto> response = resttemplate.getForEntity(url + inventory.getProdId(), ProductDto.class);

		if (response == null)
			return ResponseEntity.ok("Product does not exist");
		else {
			ProductDto productdto = response.getBody();
			if (productdto.getPid() != null) {
				inventory.setRecordedDate(LocalDateTime.now());
				Inventory invent = inventrepo.save(inventory);
				return ResponseEntity.ok(invent);
			} else
				return null;
		}
	}

	public Inventory viewInventoryItemsByPid(Integer pid) {
		Optional<Inventory> invent = inventrepo.findByPid(pid);
		if (invent.isPresent() == true) {
			Inventory inventory = invent.get();
			return inventory;
		} else
			return null;
	}

	public Inventory viewInventoryItems(Integer iid) {
		Optional<Inventory> invent = inventrepo.findById(iid);
		if (invent.isPresent() == true) {
			Inventory inventory = invent.get();
			return inventory;
		} else
			return null;
	}

	public List<Inventory> viewInventoryItems() {

		List<Inventory> invent = inventrepo.findAll();
		if (invent != null) {
			return invent;
		} else
			return null;
	}

	public Inventory updateInventoryItems(Inventory inventory, Integer iid) {
		Optional<Inventory> invent = inventrepo.findById(iid);
		if (invent.isPresent() == true) {
			Inventory inventtt = invent.get();
			if (inventory.getProdId() != null) {
				if (inventory.getProdId() != (inventtt.getProdId()))
					inventtt.setProdId(inventory.getProdId());
			}
			if (inventory.getQty() != (inventtt.getQty()))
				inventtt.setQty(inventory.getQty());

			if (inventory.getStockOut() != (inventtt.getStockOut()))
				inventtt.setStockOut(inventory.getStockOut());

			inventtt.setRecordedDate(LocalDateTime.now());
			return inventrepo.save(inventtt);
		} else
			return null;
	}

	public String increseQty(List<OrderProductDto> prodIdList) {
		for (OrderProductDto op : prodIdList) {
			Optional<Inventory> invent = inventrepo.findByPid(op.getPid());

			if (invent.isPresent() == true) {
				Inventory inventory2 = invent.get();
				inventory2.setQty(op.getQty() + inventory2.getQty());
				inventory2.setStockOut(-Integer.valueOf(op.getQty().toString()) + inventory2.getStockOut());
				inventrepo.save(inventory2);

			} else {
				return null;
			}
			
		}
		return "Update inventory as the order is cancled";
	}
}
