package art.sales.ArtsalesManagement.dto.repository;

import art.sales.ArtsalesManagement.dto.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(long orderId);
}
