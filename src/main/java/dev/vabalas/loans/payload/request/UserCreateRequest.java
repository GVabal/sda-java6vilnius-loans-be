package dev.vabalas.loans.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.vabalas.loans.entity.Role;
import dev.vabalas.loans.entity.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserCreateRequest {

    @NotBlank(message = "username must be set")
    private final String username;

    @NotBlank(message = "email must be set")
    @Email
    private final String email;

    @NotBlank(message = "password must be set")
    private final String password;

    @JsonCreator
    public UserCreateRequest(
            @JsonProperty("username") String username,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User asUser(String hashedPassword, Role role) {
        return new User(username, email, hashedPassword, role);
    }
}
