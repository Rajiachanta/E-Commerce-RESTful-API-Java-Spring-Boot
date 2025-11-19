package ecommerce.api.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ecommerce.api.customer.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
