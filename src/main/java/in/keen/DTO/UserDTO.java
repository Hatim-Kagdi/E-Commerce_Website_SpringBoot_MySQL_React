package in.keen.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import in.keen.Entity.AppRole;

public class UserDTO {
	
	private int userId;
	private String userName;
	private String userEmail;
	private Long userMobileNumber;
	private String userAddress;
	private LocalDate userBirthDate;
	private AppRole role;
	private LocalDateTime userCreatedAt;
	private LocalDateTime userUpdatedAt;
	
	public UserDTO(int userId, String userName, String userEmail, Long userMobileNumber, String userAddress,
			LocalDate userBirthDate, AppRole role, LocalDateTime userCreatedAt, LocalDateTime userUpdatedAt) {
		
		super();
		System.out.println("UserDTO called!");
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userMobileNumber = userMobileNumber;
		this.userAddress = userAddress;
		this.userBirthDate = userBirthDate;
		this.role = role;
		this.userCreatedAt = userCreatedAt;
		this.userUpdatedAt = userUpdatedAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getUserMobileNumber() {
		return userMobileNumber;
	}

	public void setUserMobileNumber(Long userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public LocalDate getUserBirthDate() {
		return userBirthDate;
	}

	public void setUserBirthDate(LocalDate userBirthDate) {
		this.userBirthDate = userBirthDate;
	}

	public AppRole getRole() {
		return role;
	}

	public void setRole(AppRole role) {
		this.role = role;
	}

	public LocalDateTime getUserCreatedAt() {
		return userCreatedAt;
	}

	public void setUserCreatedAt(LocalDateTime userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}

	public LocalDateTime getUserUpdatedAt() {
		return userUpdatedAt;
	}

	public void setUserUpdatedAt(LocalDateTime userUpdatedAt) {
		this.userUpdatedAt = userUpdatedAt;
	}

	public UserDTO() {
	}
	
	
}
