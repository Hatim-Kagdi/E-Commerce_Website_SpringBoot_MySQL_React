package in.keen.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.keen.Entity.User;
import in.keen.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Transactional
	public User registerUser(User user) {
		String hashedPassword = passwordEncoder.encode(user.getUserPassword());
		user.setUserPassword(hashedPassword);
		return userRepository.save(user);
	}
	
	public Optional<User> loginUser(String email, String rawPassword){
		Optional<User> userOpt = userRepository.findByUserEmail(email);
		
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			
			if(passwordEncoder.matches(rawPassword, user.getUserPassword())){
			return Optional.of(user);
			}
		}
		return Optional.empty();
	}
}
