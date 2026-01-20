package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // Example: Create a custom bean
    @Bean
    public String applicationName() {
        return "Insurtech Platform";
    }
}
