package za.co.bmw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.bmw.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

