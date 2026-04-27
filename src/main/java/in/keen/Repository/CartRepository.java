package in.keen.Repository;

import org.springframework.stereotype.Repository;

import in.keen.Entity.Cart;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	Optional<Cart> findByCustomerUserIdAndProductProductId(int userId, int productId);

	List<Cart> findByCustomerUserId(int userId);
	
}
