package in.keen.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.keen.DTO.CustomerDTO;
import in.keen.Service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/products")
	public ResponseEntity<?> getAllProducts() {
		List<CustomerDTO> list = customerService.getAllProductsForCustomer();
		if(list != null && !list.isEmpty()) {
			return ResponseEntity.ok(list);
		}else {
			return ResponseEntity.status(404).body(Map.of("message", "No Products Found!"));
		}
	}
}
