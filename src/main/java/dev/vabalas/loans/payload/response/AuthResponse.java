package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private final String accessToken;
    private final String refreshToken;
    private final UserResponse user;

    public static AuthResponse from(String accessToken, String refreshToken, User user) {
        return new AuthResponse(accessToken, refreshToken, UserResponse.fromUser(user));
    }

}
