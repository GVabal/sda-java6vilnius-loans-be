package dev.vabalas.loans.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private final String accessToken;
    private final String refreshToken;
    private final UserResponse user;
}
