package in.keen.DTO;

import java.time.LocalDateTime;

public class OrderDTO {
	
	private int orderId;
	private String productName;
	private double productPrice;
	
	private String userName;
	private String userEmail;
	private String userAddress;
	private Long userMobileNumber;
	
	private int quantity;
	private String orderStatus;
	private double orderTotal;
	private LocalDateTime orderDate;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
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
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public Long getUserMobileNumber() {
		return userMobileNumber;
	}
	public void setUserMobileNumber(Long userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}
	
	
	
	public OrderDTO(int orderId, String productName, double productPrice, String userName, String userEmail,
			String userAddress, Long userMobileNumber, int quantity, String orderStatus, double orderTotal,
			LocalDateTime orderDate) {
		super();
		this.orderId = orderId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.userMobileNumber = userMobileNumber;
		this.quantity = quantity;
		this.orderStatus = orderStatus;
		this.orderTotal = orderTotal;
		this.orderDate = orderDate;
	}
	public OrderDTO() {
	}
}
