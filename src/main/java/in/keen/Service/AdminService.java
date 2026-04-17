package in.keen.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.keen.Entity.AppRole;
import in.keen.Entity.User;
import in.keen.Repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(AppRole role){
		if(role == null) {
		return userRepository.findAll();
		}else {
			return userRepository.findByRole(role);
		}
	}
	
	public Optional<User> getUserById(int userId) {
		Optional<User> userOpt = userRepository.findById(userId);
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			return Optional.of(user);
		}else {
		return Optional.empty();
		}
	}
	
	@Transactional
	public boolean deleteUser(int userId){
		Optional<User> userOpt = userRepository.findById(userId);
		if(userOpt.isPresent()) {
			userRepository.delete(userOpt.get());
			return true;
		}else {
			return false;
		}
	}
	
	@Transactional
	public Optional<User> updateUser(int userId, User updatedUser) {
		Optional<User> userOpt = userRepository.findById(userId);
		
		if(userOpt.isPresent()) {
			User existingUser = userOpt.get();
			
			existingUser.setUserName(updatedUser.getUserName());
			existingUser.setUserEmail(updatedUser.getUserEmail());
			existingUser.setUserMobileNumber(updatedUser.getUserMobileNumber());
			existingUser.setUserAddress(updatedUser.getUserAddress());
			existingUser.setUserBirthDate(updatedUser.getUserBirthDate());
			
			return Optional.of(userRepository.save(existingUser));
		}
		return Optional.empty();
	}
}
