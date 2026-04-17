package in.keen.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart")
@Data

public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private int cartId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "product_quantity")
	private int quantity;
	
	@CreationTimestamp
	@Column(name = "cart_created_at")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "cart_updated_at")
	private LocalDateTime updatedAt;

}
