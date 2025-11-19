package ecommerce.api.customer.repository;

import ecommerce.api.customer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email); // this will help us later for login
}
