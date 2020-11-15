package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.ApplicationStatus;
import dev.vabalas.loans.entity.LoanApplication;

public class UserLoanApplicationResponse {
    private final Integer amount;
    private final String status;

    public UserLoanApplicationResponse(Integer amount, ApplicationStatus status) {
        this.amount = amount;
        this.status = status.getName().name();
    }

    public static UserLoanApplicationResponse fromLoanApplication(LoanApplication loanApplication) {
        return new UserLoanApplicationResponse(loanApplication.getAmount(), loanApplication.getStatus());
    }

    public Integer getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
