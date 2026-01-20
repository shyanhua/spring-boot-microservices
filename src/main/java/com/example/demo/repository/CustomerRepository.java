package com.example.demo.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;

@Repository // Tells Spring this is a repository bean
public interface  CustomerRepository extends JpaRepository<Customer, Long>{
    // Spring Data JPA provides: save(), findById(), findAll(), deleteById() automatically!
    
    // Custom query methods - Spring generates SQL automatically!
    Optional<Customer> findByEmail(String email);
    // Method name convention: findBy + FieldName
}
