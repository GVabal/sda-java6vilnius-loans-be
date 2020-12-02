package dev.vabalas.loans.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amountToRepay;
    private BigDecimal amountPayed;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "loan_application_id", nullable = false)
    private LoanApplication loanApplication;

    public Loan(BigDecimal amountToRepay, Customer customer, LoanApplication loanApplication) {
        this.amountToRepay = amountToRepay;
        this.amountPayed = BigDecimal.ZERO;
        this.status = Status.ACTIVE;
        this.customer = customer;
        this.loanApplication = loanApplication;
    }
}
