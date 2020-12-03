package dev.vabalas.loans.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class EmployeeTest {

    @Test
    void constructor_shouldConstructCorrectObject() {
        Employee employee = new Employee(1L, "P123456", "email@email.com");

        assertThat(employee.getId()).isEqualTo(1);
        assertThat(employee.getPid()).isEqualTo("P123456");
        assertThat(employee.getEmail()).isEqualTo("email@email.com");
        assertThat(employee.getStatus()).isEqualTo(Status.ACTIVE);
    }
}
