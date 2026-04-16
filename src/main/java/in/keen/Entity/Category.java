package in.keen.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
@SQLDelete(sql = "UPDATE categories SET is_deleted = true, product_deleted_at = NOW() WHERE category_id=?")
@SQLRestriction("is_deleted = false")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "category_name", nullable = false)
	private String categoryName;
	
	@Column(name = "category_description")
	private String categoryDescription;
	
	@Column(name = "is_deleted")
	private boolean isDeleted = false;
	
	@Column(name = "product_deleted_at")
	private LocalDateTime deletedAt;
	
	@CreationTimestamp
	@Column(name = "product_created_at", updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "product_updated_at")
	private LocalDateTime updatedAt;
}
