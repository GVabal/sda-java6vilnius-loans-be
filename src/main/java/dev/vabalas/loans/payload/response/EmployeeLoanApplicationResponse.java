package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.LoanApplication;

public class EmployeeLoanApplicationResponse {
    private final Long id;
    private final Integer amount;
    private final Customer customer;

    public EmployeeLoanApplicationResponse(Long id, Integer amount, Customer customer) {
        this.id = id;
        this.amount = amount;
        this.customer = customer;
    }

    public static EmployeeLoanApplicationResponse fromLoanApplication(LoanApplication loanApplication) {
        return new EmployeeLoanApplicationResponse(loanApplication.getId(), loanApplication.getAmount(), loanApplication.getAppliedBy());
    }

    public Long getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public Customer getCustomer() {
        return customer;
    }
}
