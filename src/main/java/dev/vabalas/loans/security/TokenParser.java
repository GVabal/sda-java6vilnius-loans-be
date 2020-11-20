package dev.vabalas.loans.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TokenParser {

    private final TokenProperties tokenProperties;

    public String removePrefix(String tokenWithPrefix) {
        return tokenWithPrefix.replaceFirst(String.format("^%s ", tokenProperties.getPrefix()), "");
    }

    public Optional<Claims> parseClaims(String token) {
        try {
            return Optional.of(
                    Jwts.parser()
                            .setSigningKey(Keys.hmacShaKeyFor(tokenProperties.getSecret().getBytes()))
                            .parseClaimsJws(token)
                            .getBody());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public String extractEmailString(String token) {
        return parseEmail(removePrefix(token));
    }

    public String parseEmail(String token) {
        return parseClaims(token).map(Claims::getSubject).orElse(null);
    }

    public Date parseCreatedAt(String token) {
        return parseClaims(token)
                .map(claims -> (Long) claims.get("created"))
                .map(Date::new)
                .orElse(null);
    }

    public TokenType parseTokenType(String token) {
        return parseClaims(token)
                .map(claims -> claims.get("token_type").toString())
                .map(TokenType::valueOf)
                .orElse(TokenType.ACCESS_TOKEN);
    }

    public Date parseExpirationDate(String token) {
        return parseClaims(token).map(Claims::getExpiration).orElse(null);
    }
}
