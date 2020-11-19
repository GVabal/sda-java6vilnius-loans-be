package dev.vabalas.loans.entity;

import dev.vabalas.loans.enums.ApplicationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// TODO include loanReason, termMonths, interestRatePerYear
@Data
@NoArgsConstructor
@Entity
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;
    private boolean isTakenByCustomer;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee approvedBy;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer appliedBy;

    public LoanApplication(Integer amount, boolean isTakenByCustomer, ApplicationStatus status, Employee approvedBy, Customer appliedBy) {
        this.amount = amount;
        this.isTakenByCustomer = isTakenByCustomer;
        this.status = status;
        this.approvedBy = approvedBy;
        this.appliedBy = appliedBy;
    }
}
