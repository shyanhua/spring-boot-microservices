package com.example.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
     // Secret key for signing JWT (in production, store this in environment variable!)
     private static final String SECRET_KEY = "MySecretKeyForJWTTokenGenerationMustBeLongEnough12345";
     private static final long EXPIRATION_TIME  = 86400000;

     private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

     // Generate JWT Token
     public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        return Jwts.builder()
        .claims(claims)
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date (System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(key)
        .compact();
     }

     // Extract username from tolen
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
    
    // Extract role from token
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }
    
    // Check if token is expired
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
    
    // Validate token
    public boolean validateToken(String token, String username) {
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }
    
    // Extract all claims from token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
