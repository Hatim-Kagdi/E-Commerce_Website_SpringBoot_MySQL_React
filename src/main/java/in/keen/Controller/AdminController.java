package in.keen.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.keen.DTO.UserDTO;
import in.keen.Entity.AppRole;
import in.keen.Entity.User;
import in.keen.Service.AdminService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(required = false) AppRole role){
		System.out.println("AdminController called!");
		List<UserDTO> list = adminService.getAllUsers(role);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getUserById(
			@PathVariable int userId){
		Optional<User> userOpt =  adminService.getUserById(userId);
		
		if(userOpt.isPresent()) {
			return ResponseEntity.ok(userOpt.get());
		}else {
			return ResponseEntity.status(404).body("User Not Found!");
		}
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<?> updateUser(
			@PathVariable int userId,
			@RequestBody User updatesUser){
		Optional<User> userOpt = adminService.updateUser(userId, updatesUser);
		
		if(userOpt.isPresent()) {
			return ResponseEntity.ok(userOpt.get());
		}else {
			return ResponseEntity.status(404).body("User not found!");
		}
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId){
			boolean delete = adminService.deleteUser(userId);
			
			if(delete == true) {
				return ResponseEntity.ok().body("User deleted!");
		}else {
			return ResponseEntity.status(404).body("User not deleted!");
		}
	}
}
