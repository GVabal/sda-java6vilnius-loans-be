package dev.vabalas.loans.entity;

import dev.vabalas.loans.enums.ApplicationStatus;
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
    private boolean isTakenByCustomer;
    private String loanReason;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee approvedBy;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer appliedBy;
}
