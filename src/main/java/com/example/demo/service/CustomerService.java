package com.example.demo.service;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service // Tells Spring this is a service bean
public class CustomerService {
    private final CustomerRepository customerRepository;

    // Constructor injection (PREFERRED WAY!)
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Business logic methods
    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", id));
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional // The @Transactional annotation ensures that database operations are atomic - either all operations succeed, or all fail and rollback.
    public Customer createCustomer(Customer customer) {
        // Add business validation here
        return customerRepository.save(customer);
    }
}
