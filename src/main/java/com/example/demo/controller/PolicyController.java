package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PolicyDTO;
import com.example.demo.model.Policy;
import com.example.demo.service.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {
    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public ResponseEntity<List<PolicyDTO>> getAllPolicy() {
        List<Policy> policies = policyService.getAllPolicies();
        List<PolicyDTO> policyDTOs = policies.stream()
                                                .map(this::convertToDTO)
                                                .collect(Collectors.toList());
        return ResponseEntity.ok(policyDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyDTO> getPolicyById(@PathVariable Long id) {
        Policy policy = policyService.getPolicyById(id);
        return ResponseEntity.ok(convertToDTO(policy));
    }

    @PostMapping
    public ResponseEntity<PolicyDTO> addPolicy(@RequestBody @Valid PolicyDTO policyDTO) {
        Policy policy = convertToEntity(policyDTO);
        Policy added = policyService.addPolicy(policy);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(added));
    }

    
    private PolicyDTO convertToDTO(Policy policy) {
        return PolicyDTO.builder()
        .id(policy.getId())
        .policyType(policy.getPolicyType())
        .policyNumber(policy.getPolicyNumber())
        .policyStatus(policy.getPolicyStatus())
        .policyPremium(policy.getPolicyPremium())
        .endDate(policy.getEndDate())
        .startDate(policy.getStartDate())
        .build();
    }

    private Policy convertToEntity(PolicyDTO policyDTO) {
        return Policy.builder()
        .id(policyDTO.getId())
        .policyType(policyDTO.getPolicyType())
        .policyNumber(policyDTO.getPolicyNumber())
        .policyStatus(policyDTO.getPolicyStatus())
        .policyPremium(policyDTO.getPolicyPremium())
        .endDate(policyDTO.getEndDate())
        .startDate(policyDTO.getStartDate())
        .build();
    }
}
