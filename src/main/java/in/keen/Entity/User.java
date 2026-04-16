package in.keen.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
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
}


