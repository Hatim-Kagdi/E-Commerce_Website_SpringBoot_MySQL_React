package in.keen.Entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.*;
import jakarta.persistence.*;

@Entity
@Table(name = "cart")
@SQLDelete(sql = "UPDATE cart SET is_deleted = true WHERE cart_id = ?")
@SQLRestriction("is_deleted = false")

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
	private int productQuantity;
	
	@Transient
	private double productTotal;
	
	@CreationTimestamp
	@Column(name = "cart_created_at", updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "cart_updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "is_deleted")
	private boolean isDeleted = false;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getProductTotal() {
		if(this.productTotal != 0.0) {
			return this.productQuantity * this.product.getProductPrice();
		}
		return 0.0;
	}

	public void setProductTotal(double total) {
		this.productTotal = productTotal;
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
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Cart(int cartId, User customer, Product product, int productQuantity, double productTotal,
			LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.product = product;
		this.productQuantity = productQuantity;
		this.productTotal = productTotal;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isDeleted = isDeleted;
	}

	public Cart() {
	}

	
}
