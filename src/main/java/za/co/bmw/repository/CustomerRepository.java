package za.co.bmw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.bmw.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //List<Customer> findByLastName(String lastName);
    // You can add custom methods specific to this repository here
}

