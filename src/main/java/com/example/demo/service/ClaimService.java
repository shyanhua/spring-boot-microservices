package com.example.demo.service;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Claim;
import com.example.demo.repository.ClaimRepository;

@Service // Tells Spring this is a service bean
public class ClaimService {
    private final ClaimRepository claimRepository;

    // Constructor injection (PREFERRED WAY!)
    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    // Business logic methods
    @Transactional(readOnly = true)
    public Claim getClaimById(Long id) {
        return claimRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Claim" , id));
    }

    @Transactional(readOnly = true)
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    @Transactional // The @Transactional annotation ensures that database operations are atomic - either all operations succeed, or all fail and rollback.
    public Claim createClaim(Claim claim) {
        // Add business validation here
        return claimRepository.save(claim);
    }
}
