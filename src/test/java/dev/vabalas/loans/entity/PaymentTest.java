package dev.vabalas.loans.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

class PaymentTest {

    @Test
    void constructor_shouldConstructCorrectObject() {
        Loan loan = new Loan();
        Customer customer = new Customer();
        Payment payment = new Payment(PaymentType.INCOMING, BigDecimal.TEN, loan, customer);

        assertThat(payment.getId()).isNull();
        assertThat(payment.getPaymentType()).isEqualTo(PaymentType.INCOMING);
        assertThat(payment.getAmount()).isEqualTo(BigDecimal.TEN);
        assertThat(payment.getLoan()).isEqualTo(loan);
        assertThat(payment.getCustomer()).isEqualTo(customer);
        assertThat(payment.getTimestamp())
                .isBeforeOrEqualTo(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }
}
