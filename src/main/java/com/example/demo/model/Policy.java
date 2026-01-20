package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import com.example.demo.model.enums.PolicyStatus;
import com.example.demo.model.enums.PolicyType;

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
@Table(name = "policies")
public class Policy {
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
    private String policyNumber;

    @Column(nullable = false)
    private PolicyType policyType;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;
    
    @Column(nullable = false)
    private BigDecimal policyPremium;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)  
    private PolicyStatus policyStatus;

    // orphanRemoval = true: If a claim is removed from the policy's claims list, delete it from database
    // @ManyToMany: Creates a join table "policy_coverage" with two foreign keys
    // Used Set for coverages to avoid duplicates
    @ManyToOne  // Many policies belong to one customer
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Claim> claims = new ArrayList<>();

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Premium> premiums = new ArrayList<>();

    @ManyToMany  // Many policies can have many coverages
    @JoinTable(
        name = "policy_coverage",  // Join table name
        joinColumns = @JoinColumn(name = "policy_id"),
        inverseJoinColumns = @JoinColumn(name = "coverage_id")
    )

    private Set<Coverage> coverages = new HashSet<>();

    public void addClaim(Claim claim) {
        claims.add(claim);
        claim.setPolicy(this);
    }
    public void removeClaim(Claim claim) {
        claims.remove(claim);
        claim.setPolicy(null);
    }

    public void addCoverage(Coverage coverage) {
        coverages.add(coverage);
        coverage.getPolicies().add(this);
    }

    public void removeCoverage(Coverage coverage) {
        coverages.remove(coverage);
        coverage.getPolicies().remove(this);
    }
}
