package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.LoanApplication;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EmployeeLoanApplicationResponse {
    private final Long id;
    private final Integer amount;
    private final Integer termMonths;
    private final Float interestRatePerYear;
    private Integer monthlyIncome;
    private Integer monthlyLiabilities;
    private final String loanReason;
    private final Customer customer;
    private final LocalDateTime datetimeApplied;

    public static EmployeeLoanApplicationResponse fromLoanApplication(LoanApplication loanApplication) {
        return new EmployeeLoanApplicationResponse(
                loanApplication.getId(),
                loanApplication.getAmount(),
                loanApplication.getTermMonths(),
                loanApplication.getInterestRatePerYear(),
                loanApplication.getMonthlyIncome(),
                loanApplication.getMonthlyLiabilities(),
                loanApplication.getLoanReason(),
                loanApplication.getAppliedBy(),
                loanApplication.getDatetimeApplied()
        );
    }
}
