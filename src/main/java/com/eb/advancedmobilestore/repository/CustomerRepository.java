package com.eb.advancedmobilestore.repository;


import com.eb.advancedmobilestore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findCustomerByEmailAddress(String emailAddress);
}
