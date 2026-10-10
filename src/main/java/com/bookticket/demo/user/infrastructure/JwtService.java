package com.bookticket.demo.user.infrastructure;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bookticket.demo.user.domain.Role;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtService {
	private SecretKey secretKey;

    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(String subject, Role role) {
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        Date issuedAt = new Date();
        return io.jsonwebtoken.Jwts.builder()
                .setSubject(subject)
                .claim("role",role.name())
                .expiration(expiration)
                .issuedAt(issuedAt)
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            io.jsonwebtoken.Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractSubject(String token) {
        return io.jsonwebtoken.Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public String extractRole(String token) {
        return io.jsonwebtoken.Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }
}

