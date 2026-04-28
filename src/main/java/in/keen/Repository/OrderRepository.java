package in.keen.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.keen.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByCustomerUserId(int userId);

	List<Order> findByVendorUserId(int userId);

}
