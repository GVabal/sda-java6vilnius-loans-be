package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.User;

public class AuthResponse {
    private final String accessToken;
    private final String refreshToken;
    private final UserResponse user;

    public AuthResponse(String accessToken, String refreshToken, UserResponse user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public static AuthResponse from(String accessToken, String refreshToken, User user) {
        return new AuthResponse(accessToken, refreshToken, UserResponse.fromUser(user));
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public UserResponse getUser() {
        return user;
    }
}
