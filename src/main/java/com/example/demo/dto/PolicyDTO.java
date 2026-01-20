package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.demo.model.enums.PolicyStatus;
import com.example.demo.model.enums.PolicyType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDTO {
    private Long id;
    @NotNull(message = "Policy number is required")
    private String policyNumber;
    @NotNull(message = "Policy type is required")
    private PolicyType policyType;
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @NotNull(message = "End date is required")
    private LocalDate endDate; 
    @NotNull(message = "Premium amount is required")
    @Positive(message = "Premium must be positive")
    private BigDecimal policyPremium;
    private PolicyStatus policyStatus;
}
