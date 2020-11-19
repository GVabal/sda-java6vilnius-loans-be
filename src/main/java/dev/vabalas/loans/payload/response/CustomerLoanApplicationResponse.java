package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.LoanApplication;
import dev.vabalas.loans.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerLoanApplicationResponse {
    private final Long id;
    private final Integer amount;
    private Integer termMonths;
    private Float interestRatePerYear;
    private String loanReason;
    private final ApplicationStatus status;

    public static CustomerLoanApplicationResponse fromLoanApplication(LoanApplication loanApplication) {
        return new CustomerLoanApplicationResponse(
                loanApplication.getId(),
                loanApplication.getAmount(),
                loanApplication.getTermMonths(),
                loanApplication.getInterestRatePerYear(),
                loanApplication.getLoanReason(),
                loanApplication.getStatus()
        );
    }
}
