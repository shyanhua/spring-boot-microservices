package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.demo.model.enums.ClaimStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                    // Generates getters, setters, toString, equals, hashCode
@Builder                 // Allows builder pattern: Customer.builder().name("John").build()
@NoArgsConstructor       // Generates no-args constructor
@AllArgsConstructor      // Generates constructor with all fields
@Entity                  // Marks this as a JPA entity
@Table(name = "claims")
public class Claim {
    @Id
    @GeneratedValue
    private Long id;

    /*What is Optimistic Locking?
    Problem: Two users edit the same record at the same time:
    User A reads Customer (version = 1)
    User B reads Customer (version = 1)
    User A saves → version becomes 2
    User B tries to save → CONFLICT! They're updating an old version (1), but current is 2
    Solution: Add a @Version field. Hibernate automatically:
    Increments version on every update
    Throws OptimisticLockException if versions don't match
    */
    @Version
    private Long version;

    @Column(nullable = false)
    private String claimNumber;

    @Column(nullable = false)
    private LocalDate claimDate;

    @Column(nullable = false)
    private BigDecimal claimAmount;

    @Column(nullable = false)
    private ClaimStatus claimStatus;

    private String claimDescription;

    @ManyToOne  // Many claims belong to one policy
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;
}
