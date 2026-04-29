package in.keen.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.keen.DTO.VendorStatsDTO;
import in.keen.Service.VendorService;

@RestController
@RequestMapping("/vendor")
@CrossOrigin(origins = "http://localhost:3000")
public class VendorController {
	
	@Autowired private VendorService vendorService;
	
	@GetMapping("/analytics/{userId}")
	public ResponseEntity<?> getVendorAnalytics(@PathVariable int userId){
		try {
			VendorStatsDTO dto = vendorService.getVendorAnalytics(userId);
			return ResponseEntity.ok(dto);
		}catch(Exception e) {
			return ResponseEntity.status(404).body(Map.of("message" , "Not Found!"));
		}
	}
}
