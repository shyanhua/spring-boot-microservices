package com.example.demo.controller;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClaimDTO;
import com.example.demo.model.Claim;
import com.example.demo.service.ClaimService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping
    public ResponseEntity<List<ClaimDTO>> getAllClaims() {
        List<Claim> claims = claimService.getAllClaims();
        List<ClaimDTO> claimDTOs = claims.stream()
                                            .map(this::convertToDTO)
                                            .collect(Collectors.toList()); 
        return ResponseEntity.ok(claimDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaimDTO> getClaimById(@PathVariable Long id) {
        Claim claims = claimService.getClaimById(id);
        
        return ResponseEntity.ok(convertToDTO(claims));
    }

    @PostMapping
    public ResponseEntity<ClaimDTO> addClaim(@RequestBody @Valid ClaimDTO claimDTO) {
        Claim claims = convertToEntity(claimDTO);
        Claim added = claimService.createClaim(claims);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(added));
    }

    private ClaimDTO convertToDTO(Claim claim) {
        return ClaimDTO.builder()
        .id(claim.getId())
        .claimAmount(claim.getClaimAmount())
        .claimDate(claim.getClaimDate())
        .claimDescription(claim.getClaimDescription())
        .claimNumber(claim.getClaimNumber())
        .claimStatus(claim.getClaimStatus())
        .build();
    }
    
    private Claim convertToEntity(ClaimDTO claimDTO) {
        return Claim.builder()
        .id(claimDTO.getId())
        .claimAmount(claimDTO.getClaimAmount())
        .claimDate(claimDTO.getClaimDate())
        .claimDescription(claimDTO.getClaimDescription())
        .claimNumber(claimDTO.getClaimNumber())
        .claimStatus(claimDTO.getClaimStatus())
        .build();
    }
}
