package in.keen.DTO;

public class CartDTO {
	private int cartId;
	private int productQuantity;
	private double productTotal;
	private int productId;
	private String productName;
	private double productPrice;
	private int productStock;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public double getProductTotal() {
		return productTotal;
	}
	public void setProductTotal(double productTotal) {
		this.productTotal = productTotal;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	public int getProductStock() {
		return productStock;
	}
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	
	public CartDTO(int cartId, int productQuantity, double productTotal, int productId, String productName,
			double productPrice, int productStock) {
		this.cartId = cartId;
		this.productQuantity = productQuantity;
		this.productTotal = productTotal;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productStock = productStock;
	}
	public CartDTO() {
		
	}
	
	
}
