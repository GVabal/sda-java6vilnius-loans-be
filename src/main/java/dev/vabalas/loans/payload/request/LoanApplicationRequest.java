package dev.vabalas.loans.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.vabalas.loans.entity.ApplicationStatus;
import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.LoanApplication;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoanApplicationRequest {
    @NotNull(message = "Amount must be set.")
    @Min(value = 100, message = "Amount must be at least 100")
    @Max(value = 15000, message = "Amount must be no more than 15000")
    private Integer amount;
    @NotNull(message = "Term must be set")
    @Min(value = 6, message = "Term must be at least 6 months")
    @Max(value = 60, message = "Term cannot be more than 60 months")
    private Integer termMonths;
    @NotBlank(message = "Loan reason must be set")
    private String loanReason;

    @JsonCreator
    public LoanApplicationRequest(
            @JsonProperty("amount") Integer amount,
            @JsonProperty("termMonths") Integer termMonths,
            @JsonProperty("loanReason") String loanReason) {
        this.amount = amount;
        this.termMonths = termMonths;
        this.loanReason = loanReason;
    }

    public LoanApplication toLoanApplication(Customer customer) {
        return new LoanApplication(
                amount,
                termMonths,
                20F, // interest rate is hardcoded for now
                loanReason,
                ApplicationStatus.PENDING,
                null,
                customer
        );
    }
}
