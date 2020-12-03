package dev.vabalas.loans.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@Entity
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;
    private Integer termMonths;
    private Float interestRatePerYear;
    private Integer monthlyIncome;
    private Integer monthlyLiabilities;
    private String loanReason;
    private LocalDateTime datetimeApplied;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee approvedBy;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer appliedBy;

    public LoanApplication(Integer amount, Integer termMonths, Float interestRatePerYear,
                           Integer monthlyIncome, Integer monthlyLiabilities,
                           String loanReason, Customer appliedBy) {
        this.amount = amount;
        this.termMonths = termMonths;
        this.interestRatePerYear = interestRatePerYear;
        this.monthlyIncome = monthlyIncome;
        this.monthlyLiabilities = monthlyLiabilities;
        this.loanReason = loanReason;
        this.status = ApplicationStatus.PENDING;
        this.approvedBy = null;
        this.appliedBy = appliedBy;
        this.datetimeApplied = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }
}
