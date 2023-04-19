package za.co.bmw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.bmw.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // You can add custom methods specific to this repository here
}

