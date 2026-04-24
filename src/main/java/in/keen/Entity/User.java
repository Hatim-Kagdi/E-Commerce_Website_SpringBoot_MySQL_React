package in.keen.Entity;

import java.time.*;
import org.hibernate.annotations.*;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET is_deleted = true, user_deleted_at = NOW() WHERE user_id=?")
@SQLRestriction("is_deleted = false")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@Column(name = "user_email", unique = true, nullable = false)
	private String userEmail;
	
	@Column(name = "user_password", nullable = false)
	private String userPassword;
	
	@Column(name = "user_mobile_number")
	private Long userMobileNumber;
	
	@Column(name = "user_address", columnDefinition = "TEXT")
	private String userAddress;
	
	@Column(name = "user_birth_date")
	private LocalDate userBirthDate;
	
	@Column(name = "is_deleted")
	private boolean isDeleted = false;
	
	@Column(name = "user_deleted_at")
    private LocalDateTime deletedAt;
	
	@Column(name = "user_role")
	@Enumerated(EnumType.STRING)
	private AppRole role;
	
	@CreationTimestamp 
    @Column(name = "user_created_at", updatable = false)
    private LocalDateTime createdAt;
	
	@UpdateTimestamp
    @Column(name = "user_updated_at")
    private LocalDateTime updatedAt;

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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public AppRole getRole() {
		return role;
	}

	public void setRole(AppRole role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	//Parameterized
	public User(int userId, String userName, String userEmail, String userPassword, Long userMobileNumber,
			String userAddress, LocalDate userBirthDate, boolean isDeleted, LocalDateTime deletedAt, AppRole role,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userMobileNumber = userMobileNumber;
		this.userAddress = userAddress;
		this.userBirthDate = userBirthDate;
		this.isDeleted = isDeleted;
		this.deletedAt = deletedAt;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	//No Args 
	public User() {
	}
}


