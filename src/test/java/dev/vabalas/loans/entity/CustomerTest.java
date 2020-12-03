package dev.vabalas.loans.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CustomerTest {

    @Test
    void constructor_shouldConstructCorrectObject() {
        Customer customer = new Customer(
                "First name",
                "Last name",
                "email@email.com",
                "LT101010101010"
        );

        assertThat(customer.getId()).isNull();
        assertThat(customer.getFirstName()).isEqualTo("First name");
        assertThat(customer.getLastName()).isEqualTo("Last name");
        assertThat(customer.getEmail()).isEqualTo("email@email.com");
        assertThat(customer.getBankAccountNumber()).isEqualTo("LT101010101010");
    }
}
