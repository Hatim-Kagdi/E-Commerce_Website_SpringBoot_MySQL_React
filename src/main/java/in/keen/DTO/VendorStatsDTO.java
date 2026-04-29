package in.keen.DTO;

public class VendorStatsDTO {
	
	private double totalRevenue;
	private int totalItemsSold;
	private long pendingOrders;
	private long totalOrders;
	
	public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public int getTotalItemsSold() {
		return totalItemsSold;
	}
	public void setTotalItemsSold(int totalItemsSold) {
		this.totalItemsSold = totalItemsSold;
	}
	public long getPendingOrders() {
		return pendingOrders;
	}
	public void setPendingOrders(long pendingOrders) {
		this.pendingOrders = pendingOrders;
	}
	public long getTotalOrders() {
		return totalOrders;
	}
	public void setTotalOrders(long totalOrders) {
		this.totalOrders = totalOrders;
	}
	
	public VendorStatsDTO(double totalRevenue, int totalItemsSold, long pendingOrders, long totalOrders) {
		super();
		this.totalRevenue = totalRevenue;
		this.totalItemsSold = totalItemsSold;
		this.pendingOrders = pendingOrders;
		this.totalOrders = totalOrders;
	}
	public VendorStatsDTO() {
	}
	
	
}
