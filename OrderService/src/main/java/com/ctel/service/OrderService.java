//
//package com.ctel.service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import com.ctel.dto.InventoryDto;
//import com.ctel.dto.ProductDto;
//import com.ctel.entity.Order;
//import com.ctel.entity.OrderProduct;
//import com.ctel.repository.OrderProductRepo;
//import com.ctel.repository.OrderRepo;
//
//@Service
//@Transactional
//public class OrderService {
//
//	@Autowired
//	OrderRepo orderrepo;
//	
//	@Autowired
//	OrderProductRepo orderprodrepo;
//	
//
//	@Autowired
//	RestTemplate resttemplate;
//
//	public Order orderValidator(Order order)
//	{
//		Double bill=0.0;
//    List<OrderProduct> orderprod = order.getProdList();
//    for(OrderProduct list : orderprod)
//    {
//    	Integer pid = list.getPid();
//    	String url = "http://localhost:8093/viewInventoryItemsByPid/";
//		ResponseEntity<InventoryDto> response = resttemplate.getForEntity(url + pid , InventoryDto.class);
//		InventoryDto inventoryDto = response.getBody();
//		
//		if(inventoryDto.getQty() > list.getQty())
//		{
//			String url2 = "http://localhost:8092/viewUserProdDetails/";
//			ResponseEntity<ProductDto> response2 = resttemplate.getForEntity(url2 +pid, ProductDto.class);
//			ProductDto productdto = response2.getBody();
//			
//			list.setPrice(productdto.getPrice() * list.getQty() );
//			list.setProdName(productdto.getProdName());
//			System.out.println("before setting"+bill);
//			System.out.println(productdto.getPrice() * list.getQty());
//			bill=bill+(productdto.getPrice() * list.getQty()) ;
//			System.out.println("after setting"+bill);
//			
//			inventoryDto.setRecordedDate(LocalDateTime.now());
//			inventoryDto.setQty(inventoryDto.getQty()-list.getQty());
//			inventoryDto.setStockOut(inventoryDto.getStockOut()+Integer.valueOf(list.getQty().toString()));
//			try {
//			String str3 = "http://localhost:8093/updateInventoryItems/";
//			System.out.println(str3);
//
//			ResponseEntity<InventoryDto> response3 = resttemplate.postForEntity(str3, inventoryDto, InventoryDto.class);
//			assertNotNull(response3.getBody());
//			assertNotNull(response3.getBody().getIid());
////			InventoryDto inventdto = response3.getBody();
//			System.out.println(response3.toString());
//			}
//			catch (HttpClientErrorException | HttpServerErrorException e) {
//			    // Handle the exception, e.g., log the error details or take appropriate action.
//			    System.err.println("HTTP Request failed with status code: " + e.getRawStatusCode());
//			    System.err.println("Response body: " + e.getResponseBodyAsString());
//			} 
//		}
//		System.out.println("after loop"+bill);
//		list.setOrderTemp(order);
////		orderprodrepo.save(list);
//		
//    }
//		
//    order.setCreatedDate(LocalDateTime.now());
//    order.setBill(bill);
//    System.out.println(order.getBill());
//    Order savedOrd=orderrepo.save(order);
//    
//    return savedOrd;	
//	}
//
//	private void assertNotNull(Integer iid) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	private void assertNotNull(InventoryDto body) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
