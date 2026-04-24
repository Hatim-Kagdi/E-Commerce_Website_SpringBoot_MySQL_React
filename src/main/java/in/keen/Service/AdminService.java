package in.keen.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.keen.DTO.UserDTO;
import in.keen.Entity.AppRole;
import in.keen.Entity.User;
import in.keen.Mapper.UserMapper;
import in.keen.Repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> getAllUsers(AppRole role) {
		System.out.println("AdminService Called");
		List<User> userList = null;
		if (role == null) {
			userList = userRepository.findAll();
		} else {
			userList = userRepository.findByRole(role);
		}
		return userList.stream()
				.map((users) -> UserMapper.mapToUserDTO(users))
				.collect(Collectors.toList());
	}

	public UserDTO getUserById(int userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found!"));

		return UserMapper.mapToUserDTO(user);
	}

	@Transactional
	public boolean deleteUser(int userId) {
		Optional<User> userOpt = userRepository.findById(userId);
		if (userOpt.isPresent()) {
			userRepository.delete(userOpt.get());
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public UserDTO updateUser(int userId, UserDTO dto) {
		Optional<User> userOpt = userRepository.findById(userId);

		if (userOpt.isEmpty()) {
			throw new RuntimeException("User not found!");
		}
		User existingUser = userOpt.get();

		existingUser.setUserName(dto.getUserName());
		existingUser.setUserEmail(dto.getUserEmail());
		existingUser.setUserMobileNumber(dto.getUserMobileNumber());
		existingUser.setUserAddress(dto.getUserAddress());
		existingUser.setUserBirthDate(dto.getUserBirthDate());

		User user = userRepository.save(existingUser);

		return UserMapper.mapToUserDTO(user);
	}
}
