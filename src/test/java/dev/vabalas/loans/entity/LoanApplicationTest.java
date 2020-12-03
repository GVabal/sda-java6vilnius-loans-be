package dev.vabalas.loans.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

class LoanApplicationTest {

    @Test
    void constructor_shouldConstructCorrectObject() {
        Customer customer = new Customer();
        LoanApplication loanApplication = new LoanApplication(
                1000,
                12,
                15.5F,
                500,
                50,
                "Reasonable",
                customer
        );

        assertThat(loanApplication.getId()).isNull();
        assertThat(loanApplication.getAmount()).isEqualTo(1000);
        assertThat(loanApplication.getTermMonths()).isEqualTo(12);
        assertThat(loanApplication.getInterestRatePerYear()).isEqualTo(15.5F);
        assertThat(loanApplication.getMonthlyIncome()).isEqualTo(500);
        assertThat(loanApplication.getMonthlyLiabilities()).isEqualTo(50);
        assertThat(loanApplication.getLoanReason()).isEqualTo("Reasonable");
        assertThat(loanApplication.getApprovedBy()).isNull();
        assertThat(loanApplication.getAppliedBy()).isEqualTo(customer);
        assertThat(loanApplication.getStatus()).isEqualTo(ApplicationStatus.PENDING);
        assertThat(loanApplication.getDatetimeApplied())
                .isBeforeOrEqualTo(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }
}
