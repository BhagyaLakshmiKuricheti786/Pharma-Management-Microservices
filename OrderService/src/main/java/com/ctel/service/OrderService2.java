
package com.ctel.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.client.RestTemplate;

import com.ctel.dto.InventoryDto;
import com.ctel.dto.ProductDto;
import com.ctel.entity.Order;
import com.ctel.entity.OrderProduct;
import com.ctel.repository.OrderProductRepo;
import com.ctel.repository.OrderRepo;

@Service
@Transactional
public class OrderService2 {

	@Autowired
	OrderRepo orderrepo;
	
	@Autowired
	OrderProductRepo orderprodrepo;
	

	@Autowired
	RestTemplate resttemplate;

	public Order orderValidator(Order order)
	{
		Double bill=0.0;
    List<OrderProduct> orderprod = order.getProdList();
    for(OrderProduct list : orderprod)
    {
    	Integer pid = list.getPid();
    	String url = "http://localhost:8093/viewInventoryItemsByPid/";
		ResponseEntity<InventoryDto> response = resttemplate.getForEntity(url + pid , InventoryDto.class);
		InventoryDto inventoryDto = response.getBody();
		
		if(inventoryDto.getQty() > list.getQty())
		{
			String url2 = "http://localhost:8092/viewUserProdDetails/";
			ResponseEntity<ProductDto> response2 = resttemplate.getForEntity(url2 +pid, ProductDto.class);
			ProductDto productdto = response2.getBody();
			
			list.setPrice(productdto.getPrice() * list.getQty() );
			list.setProdName(productdto.getProdName());
			System.out.println("before setting"+bill);
			System.out.println(productdto.getPrice() * list.getQty());
			bill=bill+(productdto.getPrice() * list.getQty()) ;
			System.out.println("after setting"+bill);
			
			inventoryDto.setRecordedDate(LocalDateTime.now());
			inventoryDto.setQty(inventoryDto.getQty()-list.getQty());
			inventoryDto.setStockOut(inventoryDto.getStockOut()+Integer.valueOf(list.getQty().toString()));
			try {
			    // Define the URL for updating inventory items
			    String updateUrl = "http://localhost:8093/updateInventoryItems/"+inventoryDto.getIid();

			    // Create an HttpEntity with the request payload (inventoryDto)
			    HttpEntity<InventoryDto> requestEntity = new HttpEntity<>(inventoryDto);

			    // Make an HTTP POST request and receive the response
			    ResponseEntity<InventoryDto> response3 = resttemplate.postForEntity(updateUrl, requestEntity, InventoryDto.class);
			    
			    // Check if the response status code indicates success (2xx)
			    if (response3.getStatusCode().is2xxSuccessful()) {
			        // Update was successful; you can process the response if needed
			        InventoryDto updatedInventory = response3.getBody();
			        
			      
			        System.out.println("Inventory updated successfully: " + updatedInventory);
			    } else {
			        // Handle non-successful responses here, such as logging or other actions
			        System.err.println("HTTP Request failed with status code: " + response3.getStatusCodeValue());
			        System.err.println("Response body: " + response3.getBody());
			    }
			} catch (Exception e) {
			    // Handle exceptions (e.g., network issues) here
			    e.printStackTrace();  
			} 
		}
		System.out.println("after loop"+bill);
		list.setOrderTemp(order);
//		orderprodrepo.save(list);
		
    }
		
    order.setCreatedDate(LocalDateTime.now());
    order.setBill(bill);
    System.out.println(order.getBill());
    Order savedOrd=orderrepo.save(order);
    
    return savedOrd;	
	}

	
	public String deleteOrder(Integer oid)
	{
		Optional<Order> orderprod = orderrepo.findById(oid);
		if(orderprod.isPresent()==true)
		{
			Order order = orderprod.get();
			List<OrderProduct> prodlist = order.getProdList();
			String url4 = "http://localhost:8093/increseQty"; 
			ResponseEntity<String> response4 = resttemplate.postForEntity(url4, prodlist , String.class) ; 
			String str = response4.getBody();
			orderrepo.deleteById(oid);
			return str;
		}
		else 
			return null;
			
	}

}
