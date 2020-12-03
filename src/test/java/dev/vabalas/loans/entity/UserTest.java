package dev.vabalas.loans.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserTest {

    @Test
    void constructor_shouldConstructCorrectObject() {
        Role role = new Role();
        User user = new User("username", "email@email.com", "password", role);

        assertThat(user.getId()).isNull();
        assertThat(user.getUsername()).isEqualTo("username");
        assertThat(user.getEmail()).isEqualTo("email@email.com");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.getRole()).isEqualTo(role);
        assertThat(user.getStatus()).isEqualTo(Status.ACTIVE);
    }
}
