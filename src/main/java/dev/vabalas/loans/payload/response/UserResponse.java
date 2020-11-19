package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private final String email;
    private final String username;
    private final String role;

    public static UserResponse fromUser(User user) {
        return new UserResponse(
                user.getEmail(), user.getUsername(), user.getRole().getName().toString());
    }
}
