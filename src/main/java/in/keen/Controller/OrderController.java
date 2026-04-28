package in.keen.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.keen.DTO.OrderDTO;
import in.keen.Service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	//CUSTOMER MODULE
	@PostMapping("/checkout/{userId}")
	public ResponseEntity<?> checkOut(@PathVariable int userId){
		try {
			orderService.checkOut(userId);
			return ResponseEntity.ok(Map.of("message", "CheckOut Succesfull"));
		}catch(Exception e) {
			return ResponseEntity.status(404).body(Map.of("message", "CheckOut Failed!"));
		}
	}
	
	@GetMapping("/placedOrder/{userId}")
	public ResponseEntity<?> getPlacedOrder(@PathVariable int userId){
		try {
			List<OrderDTO> list = orderService.getPlacedOrder(userId);
			return ResponseEntity.ok(list);
		}catch(Exception e) {
			return ResponseEntity.status(404).body(Map.of("message" ,"Failed to get Placed Orders"));
		}
	}
	
	@DeleteMapping("/cancel/{orderId}")
	public ResponseEntity<?> cancelOrder(@PathVariable int orderId){
		try {
			orderService.cancelOrder(orderId);
			return ResponseEntity.ok(Map.of("message", "Delete Succesfull!"));
		}catch(Exception e) {
			return ResponseEntity.status(404).body(Map.of("message", "Delete Failed!"));
		}
	}
	
	//VENDOR MODULE
	@GetMapping("/vendorOrder/{userId}")
	public ResponseEntity<?> getOrderForVendor(@PathVariable int userId){
		try {
			List<OrderDTO> list = orderService.getOrderForVendor(userId);
			return ResponseEntity.ok(list);
		}catch(Exception e) {
			return ResponseEntity.status(404).body(Map.of("message", "Not Found!"));
		}
	}
	
	@PostMapping("/ship/{orderId}")
			public ResponseEntity<?> shipOrder(@PathVariable int orderId){
		boolean save = orderService.shipOrder(orderId);
		if(save) {
			return ResponseEntity.ok(Map.of("message" ,"Order Shipped"));
		}else {
			return ResponseEntity.status(404).body(Map.of("message" , "Order SHip Failed!"));		}
		
	}
}
