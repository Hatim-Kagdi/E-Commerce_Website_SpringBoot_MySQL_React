package in.keen.Mapper;

import in.keen.DTO.UserDTO;
import in.keen.Entity.User;

public class UserMapper {
	public static UserDTO mapToUserDTO(User user) {
		System.out.println("UserMapper called!");
		return new UserDTO(
				user.getUserId(),
				user.getUserName(),
				user.getUserEmail(),
				user.getUserMobileNumber(),
				user.getUserAddress(),
				user.getUserBirthDate(),
				user.getRole(),
				user.getCreatedAt(),
				user.getUpdatedAt()
		);
	}
}
