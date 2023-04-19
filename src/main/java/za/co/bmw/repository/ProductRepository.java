package za.co.bmw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.bmw.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}