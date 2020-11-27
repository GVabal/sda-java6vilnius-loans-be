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

    @NotBlank(message = "Email must be set")
    @Email
    private final String email;

    @NotBlank(message = "Password must be set")
    private final String password;

    @JsonCreator
    public UserCreateRequest(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    public User asUser(String hashedPassword, Role role) {
        return new User(email, email, hashedPassword, role);
    }
}
