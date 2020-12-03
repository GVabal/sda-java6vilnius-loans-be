package dev.vabalas.loans.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class LoanTest {

    @Test
    void constructor_shouldConstructCorrectObject() {
        Customer customer = new Customer();
        LoanApplication loanApplication = new LoanApplication();
        Loan loan = new Loan(BigDecimal.TEN, customer, loanApplication);

        assertThat(loan.getId()).isNull();
        assertThat(loan.getAmountToRepay()).isEqualTo(BigDecimal.TEN);
        assertThat(loan.getAmountPayed()).isEqualTo(BigDecimal.ZERO);
        assertThat(loan.getCustomer()).isEqualTo(customer);
        assertThat(loan.getLoanApplication()).isEqualTo(loanApplication);
        assertThat(loan.getStatus()).isEqualTo(Status.ACTIVE);
    }
}
