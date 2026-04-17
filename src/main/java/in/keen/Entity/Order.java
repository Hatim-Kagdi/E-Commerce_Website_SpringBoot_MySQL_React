package in.keen.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET is_deleted = true, order_deleted_at = NOW() WHERE order_id = ?")
@SQLRestriction("is_deleted = false")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "order_amount")
	private double amount;
	
	@Column(name = "order_quantity")
	private int quantity;
	
	@Column(name = "order_total")
	private double total;
	
	@Column(name = "order_status")
	private String status;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "order_deleted_at")
	private LocalDateTime deletedAt;
	
	@CreationTimestamp
	@Column(name = "order_created_at", updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "order_updated_at")
	private LocalDateTime updatedAt;
}
