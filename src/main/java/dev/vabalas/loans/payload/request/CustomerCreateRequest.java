package dev.vabalas.loans.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.vabalas.loans.entity.Customer;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CustomerCreateRequest {

    @NotBlank(message = "First name must be set")
    private final String firstName;

    @NotBlank(message = "Last name must be set")
    private final String lastName;

    @NotBlank(message = "Email must be set")
    @Email
    private final String email;

    @NotBlank(message = "Password must be set")
    private final String password;

    @NotBlank(message = "Bank account number must be set")
    private final String bankAccountNumber;

    @JsonCreator
    public CustomerCreateRequest(@JsonProperty("firstName") String firstName,
                                 @JsonProperty("lastName") String lastName,
                                 @JsonProperty("email") String email,
                                 @JsonProperty("password") String password,
                                 @JsonProperty("bankAccountNumber") String bankAccountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.bankAccountNumber = bankAccountNumber;
    }

    public Customer toCustomer() {
        return new Customer(firstName, lastName, email, bankAccountNumber);
    }
}
