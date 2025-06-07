package com.technibook.technibook.util;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    private final Key key = Keys.hmacShaKeyFor("your-256-bit-secret-your-256-bit-secret".getBytes());

    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
            .signWith(SignatureAlgorithm.HS256, key)
            .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(token)
            .getBody();
    }

    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
