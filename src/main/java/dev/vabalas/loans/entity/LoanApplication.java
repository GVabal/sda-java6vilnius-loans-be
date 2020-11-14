package dev.vabalas.loans.entity;

import javax.persistence.*;

@Entity
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;
    private boolean isTakenByCustomer;

    @OneToOne()
    @JoinColumn(name = "status_id", nullable = false)
    private ApplicationStatus status;

    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee approvedBy;

    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer appliedBy;

    protected LoanApplication() {
    }

    public LoanApplication(Integer amount, boolean isTakenByCustomer, ApplicationStatus status, Employee approvedBy, Customer appliedBy) {
        this.amount = amount;
        this.isTakenByCustomer = isTakenByCustomer;
        this.status = status;
        this.approvedBy = approvedBy;
        this.appliedBy = appliedBy;
    }

    public Long getId() {
        return id;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public Integer getAmount() {
        return amount;
    }

    public boolean isTakenByCustomer() {
        return isTakenByCustomer;
    }

    public Employee getApprovedBy() {
        return approvedBy;
    }

    public Customer getAppliedBy() {
        return appliedBy;
    }
}
