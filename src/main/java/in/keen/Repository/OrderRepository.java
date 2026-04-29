package in.keen.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.keen.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByCustomerUserId(int userId);

	List<Order> findByVendorUserId(int userId);
	
	@Query("SELECT SUM(o.total) FROM Order o WHERE o.vendor.userId = :userId AND o.status = 'SHIPPED'")
	Double getRevenueByVendor(@Param("userId") int userId);

	@Query("SELECT SUM(o.quantity) FROM Order o WHERE o.vendor.userId = :userId AND o.status = 'SHIPPED'")
	Integer getItemsSoldByVendor(@Param("userId") int userId);

	long countByVendorUserIdAndStatus(int userId, String status);

	long countByVendorUserId(int userId);
	
}
