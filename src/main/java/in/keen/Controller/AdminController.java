package in.keen.Controller;

import java.util.List;
import java.util.Map;
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
	public ResponseEntity<UserDTO> getUserById(
			@PathVariable int userId){
		UserDTO userdto =  adminService.getUserById(userId);
		
		return ResponseEntity.ok(userdto);
		
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDTO> updateUser(
			@PathVariable int userId,
			@RequestBody UserDTO updatedUserDTO){
		
		UserDTO userdto = adminService.updateUser(userId, updatedUserDTO);
		
		return ResponseEntity.ok(userdto);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId){
			boolean delete = adminService.deleteUser(userId);
			
			if(delete == true) {
				return ResponseEntity.ok(Map.of("message" , "User Deleted!"));
		}else {
			return ResponseEntity.status(404).body("User not deleted!");
		}
	}
}
