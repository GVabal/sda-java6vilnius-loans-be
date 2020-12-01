package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.ApplicationStatus;
import dev.vabalas.loans.entity.LoanApplication;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomerLoanApplicationResponse {
    private final Long id;
    private final Integer amount;
    private final Integer termMonths;
    private final Float interestRatePerYear;
    private Integer monthlyIncome;
    private Integer monthlyLiabilities;
    private final String loanReason;
    private final ApplicationStatus status;
    private final LocalDateTime datetimeApplied;

    public static CustomerLoanApplicationResponse fromLoanApplication(LoanApplication loanApplication) {
        return new CustomerLoanApplicationResponse(
                loanApplication.getId(),
                loanApplication.getAmount(),
                loanApplication.getTermMonths(),
                loanApplication.getInterestRatePerYear(),
                loanApplication.getMonthlyIncome(),
                loanApplication.getMonthlyLiabilities(),
                loanApplication.getLoanReason(),
                loanApplication.getStatus(),
                loanApplication.getDatetimeApplied()
        );
    }
}
