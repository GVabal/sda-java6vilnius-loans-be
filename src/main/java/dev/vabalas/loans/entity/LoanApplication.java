package dev.vabalas.loans.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String loanReason;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee approvedBy;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer appliedBy;

    public LoanApplication(Integer amount, Integer termMonths, Float interestRatePerYear,
                           String loanReason, ApplicationStatus status, Employee approvedBy,
                           Customer appliedBy) {
        this.amount = amount;
        this.termMonths = termMonths;
        this.interestRatePerYear = interestRatePerYear;
        this.loanReason = loanReason;
        this.status = status;
        this.approvedBy = approvedBy;
        this.appliedBy = appliedBy;
    }
}
