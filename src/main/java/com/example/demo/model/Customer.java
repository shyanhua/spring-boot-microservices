package com.example.demo.model;

import java.time.LocalDate;
import java.util.*;

import com.example.demo.model.enums.CustomerStatus;

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
@Table(name = "customers") // Table name
public class Customer {
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
    private String firstName;

    @Column(nullable = false) 
    private String lastName;

    @Column(unique = true, nullable = false)  
    private String email;

    @Column(unique = true, nullable = false)  
    private String phone;

    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)  
    private CustomerStatus status;

    @ManyToOne  // Many customers can belong to one agent
    @JoinColumn(name = "agent_id")  // Foreign key column in customers table
    private Agent agent;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)  // One customer can have many policies
    private List<Policy> listOfPolicies = new ArrayList<>();

    // custom methods
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Helper methods to maintain bidirectional relationship
    public void addPolicy(Policy policy) {
        listOfPolicies.add(policy);
        policy.setCustomer(this);
    }

    public void removePolicy(Policy policy) {
        listOfPolicies.remove(policy);
        policy.setCustomer(null);
    }
}