package dev.vabalas.loans.payload.response;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.LoanApplication;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeLoanApplicationResponse {
    private final Long id;
    private final Integer amount;
    private Integer termMonths;
    private Float interestRatePerYear;
    private String loanReason;
    private final Customer customer;

    public static EmployeeLoanApplicationResponse fromLoanApplication(LoanApplication loanApplication) {
        return new EmployeeLoanApplicationResponse(
                loanApplication.getId(),
                loanApplication.getAmount(),
                loanApplication.getTermMonths(),
                loanApplication.getInterestRatePerYear(),
                loanApplication.getLoanReason(),
                loanApplication.getAppliedBy()
        );
    }
}
