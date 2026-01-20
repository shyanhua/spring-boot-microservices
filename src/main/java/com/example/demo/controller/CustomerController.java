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

import com.example.demo.dto.CustomerDTO;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOs = customers.stream()
                                            .map(this::convertToDTO)
                                            .collect(Collectors.toList());

        return ResponseEntity.ok(customerDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(convertToDTO(customer));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer added = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(added));
    }

    private CustomerDTO convertToDTO(Customer customer) {
        return CustomerDTO.builder()
        .id(customer.getId())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .email(customer.getEmail())
        .phone(customer.getPhone())
        .address(customer.getAddress())
        .status(customer.getStatus())
        .dateOfBirth(customer.getDateOfBirth())
        .build();
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        return Customer.builder()
        .id(customerDTO.getId())
        .firstName(customerDTO.getFirstName())
        .lastName(customerDTO.getLastName())
        .email(customerDTO.getEmail())
        .phone(customerDTO.getPhone())
        .address(customerDTO.getAddress())
        .status(customerDTO.getStatus())
        .dateOfBirth(customerDTO.getDateOfBirth())
        .build();
    }
}
