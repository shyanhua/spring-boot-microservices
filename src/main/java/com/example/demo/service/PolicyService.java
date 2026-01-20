package com.example.demo.service;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Policy;
import com.example.demo.repository.PolicyRepository;

@Service // Tells Spring this is a service bean
public class PolicyService {
    private final PolicyRepository policyRepository;

    // Constructor injection (PREFERRED WAY!)
    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    // Business logic methods
    @Transactional(readOnly = true)
    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Policy" + id));
    }

    @Transactional(readOnly = true)
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    @Transactional // The @Transactional annotation ensures that database operations are atomic - either all operations succeed, or all fail and rollback.
    public Policy addPolicy(Policy policy) {
        return policyRepository.save(policy);
    }
}
