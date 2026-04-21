package in.keen.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.keen.Config.JWTUtil;
import in.keen.DTO.UserDTO;
import in.keen.Entity.User;
import in.keen.Mapper.UserMapper;
import in.keen.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTUtil jwtutil;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Transactional
	public User registerUser(User user) {
		String hashedPassword = passwordEncoder.encode(user.getUserPassword());
		user.setUserPassword(hashedPassword);
		return userRepository.save(user);
	}
	
	public String loginUser(String email, String password) {
		try {
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password));
		String token = jwtutil.generateToken(email);
		return token;
		}catch(Exception e) {
			return null;
		}
	}

}
