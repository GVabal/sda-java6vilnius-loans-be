package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.LoanApplication;

public class EmployeeLoanApplicationResponse {
    private final Integer amount;
    private final Customer customer;

    public EmployeeLoanApplicationResponse(Integer amount, Customer customer) {
        this.amount = amount;
        this.customer = customer;
    }

    public static EmployeeLoanApplicationResponse fromLoanApplication(LoanApplication loanApplication) {
        return new EmployeeLoanApplicationResponse(loanApplication.getAmount(), loanApplication.getAppliedBy());
    }

    public Integer getAmount() {
        return amount;
    }

    public Customer getCustomer() {
        return customer;
    }
}
