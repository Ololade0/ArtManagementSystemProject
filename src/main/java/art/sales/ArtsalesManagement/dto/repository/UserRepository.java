package art.sales.ArtsalesManagement.dto.repository;

import art.sales.ArtsalesManagement.dto.model.Art;
import art.sales.ArtsalesManagement.dto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findUserByEmail(String email);
}
