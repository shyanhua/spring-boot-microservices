package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Policy;

@Repository // Tells Spring this is a repository bean
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    // Spring Data JPA provides: save(), findById(), findAll(), deleteById() automatically!
    
    // Custom query methods - Spring generates SQL automatically!
    // Method name convention: findBy + FieldName
}
