package com.wegoauthentic.app.config;

import com.wegoauthentic.app.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

public class JwtService {

    private static final String SECRET_KEY = "89B84ED6A248D68B9BD75F21D2F32";

    public String extractPhone(Claims claims) {
        return claims.get("phone",String.class);
    }

    public String extractUserId(Claims claims) {
        return claims.getSubject();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getId())
                .claim("phone", user.getPhone())
                .claim("role", user.getRole().name())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(900))) // 15 min
                .signWith(getSigningKey())
                .compact();
    }

    public boolean isTokenValid(Claims claims){
        return claims.getExpiration().after(new Date());
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
}
