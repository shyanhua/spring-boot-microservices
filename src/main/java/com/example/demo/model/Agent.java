package com.example.demo.model;

import java.math.BigDecimal;
import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                    // Generates getters, setters, toString, equals, hashCode
@Builder                 // Allows builder pattern: Customer.builder().name("John").build()
@NoArgsConstructor       // Generates no-args constructor
@AllArgsConstructor      // Generates constructor with all fields
@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String licenseNumber;

    @Column(nullable = false) 
    private BigDecimal commissionRate;

    @OneToMany(mappedBy = "agent")
    private List<Customer> customers = new ArrayList<>();

    // custom methods
    public String fullName(){
        return firstName + " " + lastName;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.setAgent(this);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
        customer.setAgent(null);
    }
}