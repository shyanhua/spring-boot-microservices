package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.demo.model.enums.PremiumStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                    // Generates getters, setters, toString, equals, hashCode
@Builder                 // Allows builder pattern: Customer.builder().name("John").build()
@NoArgsConstructor       // Generates no-args constructor
@AllArgsConstructor      // Generates constructor with all fields
@Table(name = "premiums")
@Entity
public class Premium {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)  
    private PremiumStatus status;

    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;
}
