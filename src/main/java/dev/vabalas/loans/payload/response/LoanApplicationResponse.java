package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.ApplicationStatus;
import dev.vabalas.loans.entity.LoanApplication;

public class LoanApplicationResponse {
    private final Integer amount;
    private final String status;

    public LoanApplicationResponse(Integer amount, ApplicationStatus status) {
        this.amount = amount;
        this.status = status.getName().name();
    }

    public static LoanApplicationResponse fromLoanApplication(LoanApplication loanApplication) {
        return new LoanApplicationResponse(loanApplication.getAmount(), loanApplication.getStatus());
    }

    public Integer getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
