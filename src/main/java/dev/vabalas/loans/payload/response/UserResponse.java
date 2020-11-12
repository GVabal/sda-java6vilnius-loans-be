package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.User;

public class UserResponse {
    private final String email;
    private final String username;
    private final String role;

    public UserResponse(String email, String username, String role) {
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public static UserResponse fromUser(User user) {
        return new UserResponse(
                user.getEmail(), user.getUsername(), user.getRole().getName().toString());
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
