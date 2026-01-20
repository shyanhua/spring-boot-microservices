package com.example.demo.model;

import java.math.BigDecimal;
import java.util.*;

import com.example.demo.model.enums.CoverageType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                    // Generates getters, setters, toString, equals, hashCode
@Builder                 // Allows builder pattern: Customer.builder().name("John").build()
@NoArgsConstructor       // Generates no-args constructor
@AllArgsConstructor      // Generates constructor with all fields
@Table(name = "coverage")
@Entity
public class Coverage {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private CoverageType coverageType;

    @Column(nullable = false)
    private BigDecimal coverageAmount;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "coverages")
    private Set<Policy> policies = new HashSet<>();
}
