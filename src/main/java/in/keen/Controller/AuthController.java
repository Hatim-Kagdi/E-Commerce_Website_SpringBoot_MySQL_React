package in.keen.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import in.keen.DTO.UserDTO;
import in.keen.Entity.User;
import in.keen.Service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		System.out.println("Registration request received for: " + user.getUserEmail());
		return ResponseEntity.ok(userService.registerUser(user));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> loginData){
		System.out.println("Login request received ");
		String token = userService.loginUser(loginData.get("email"), loginData.get("password"));
		
		if(token != null) {
			UserDTO user = userService.getUserByEmail(loginData.get("email"));
			return ResponseEntity.ok(Map.of(
					"token", token,
					"role" , user.getRole().name()
					));
		}else {
			return ResponseEntity.status(401).body("Login Failed!");
		}
	}
}
