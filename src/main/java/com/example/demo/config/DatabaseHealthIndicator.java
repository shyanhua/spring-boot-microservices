package com.example.demo.config;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {
    @Override
    public @Nullable Health health() {
        // Simulate checking database connection
        boolean databaseUp = true;

        if(databaseUp) {
            return Health.up()
            .withDetail("database", "In-memory storage")
            .withDetail("status", "Connected")
            .build();
        } else {
            return Health.down()
            .withDetail("error", "Cannot connect to storage")
            .build();
        }
    }
}
