package dev.vabalas.loans.security;

import dev.vabalas.loans.entity.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenValidator.class);
    private final TokenParser tokenParser;

    public boolean isValid(String token, TokenType tokenType, User user) {
        LOGGER.info("isValid({},{},{})", token, tokenType, user);
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
