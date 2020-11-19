package dev.vabalas.loans.security;

import dev.vabalas.loans.entity.User;
import dev.vabalas.loans.enums.TokenType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class TokenGenerator {

    private final TokenProperties tokenProperties;

    public String generate(User user, TokenType tokenType, long expirationSeconds) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getEmail());
        claims.put("created", generateCurrentDate());
        claims.put("token_type", tokenType.toString());
        return generate(claims, expirationSeconds);
    }

    private String generate(Map<String, Object> claims, long expirationSeconds) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpiration(expirationSeconds))
                .signWith(
                        Keys.hmacShaKeyFor(tokenProperties.getSecret().getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    private Date generateExpiration(long seconds) {
        return new Date(System.currentTimeMillis() + Duration.ofSeconds(seconds).toMillis());
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }
}
