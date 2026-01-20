package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "insurtech")
public class InsurtechProperties {
    private String companyName;
    private String version;
    private int maxPoliciesPerCustomer;
}
