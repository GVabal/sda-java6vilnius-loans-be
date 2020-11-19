package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.LoanApplication;
import dev.vabalas.loans.enums.ApplicationStatus;
import lombok.Data;

@Data
public class CustomerLoanApplicationResponse {
    private final Long id;
    private final Integer amount;
    private final ApplicationStatus status;

    public CustomerLoanApplicationResponse(Long id, Integer amount, ApplicationStatus status) {
        this.id = id;
        this.amount = amount;
        this.status = status;
    }

    public static CustomerLoanApplicationResponse fromLoanApplication(LoanApplication loanApplication) {
        return new CustomerLoanApplicationResponse(loanApplication.getId(), loanApplication.getAmount(), loanApplication.getStatus());
    }
}
