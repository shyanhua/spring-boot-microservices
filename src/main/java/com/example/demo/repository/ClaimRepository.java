package com.example.demo.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Claim;

@Repository // Tells Spring this is a repository bean
public interface ClaimRepository extends JpaRepository<Claim,Long>{
    // Spring Data JPA provides: save(), findById(), findAll(), deleteById() automatically!
    
    // Custom query methods - Spring generates SQL automatically!
    // Method name convention: findBy + FieldName
}
