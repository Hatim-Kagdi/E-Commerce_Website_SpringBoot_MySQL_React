package in.keen.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.keen.Entity.AppRole;
import in.keen.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUserEmail(String userEmail);

	List<User> findByRole(AppRole role);

}
