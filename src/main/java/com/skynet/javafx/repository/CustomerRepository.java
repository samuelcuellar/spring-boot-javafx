package com.skynet.javafx.repository;

import org.springframework.data.repository.CrudRepository;
import com.skynet.javafx.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
}
