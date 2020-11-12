package dev.vabalas.loans.security;

import dev.vabalas.loans.entity.User;
import dev.vabalas.loans.enums.TokenType;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenValidator {

    private final TokenParser tokenParser;

    public TokenValidator(TokenParser tokenParser) {
        this.tokenParser = tokenParser;
    }

    public boolean isValid(String token, TokenType tokenType, User user) {
        String email = tokenParser.parseEmail(token);
        Date createdAt = tokenParser.parseCreatedAt(token);
        TokenType tokenTypeFromToken = tokenParser.parseTokenType(token);

        return email != null
                && createdAt != null
                && email.equalsIgnoreCase(user.getEmail())
                && tokenTypeFromToken.equals(tokenType)
                && !(isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = tokenParser.parseExpirationDate(token);
        return expirationDate == null || expirationDate.before(generateCurrentDate());
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }
}
