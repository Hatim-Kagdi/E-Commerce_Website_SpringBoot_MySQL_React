package in.keen.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.keen.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByVendorUserEmail(String userEmail);

	List<Product> findByVendorUserId(int userId);
}
