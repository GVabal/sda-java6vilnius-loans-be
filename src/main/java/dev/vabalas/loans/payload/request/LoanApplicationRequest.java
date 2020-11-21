package dev.vabalas.loans.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.vabalas.loans.entity.ApplicationStatus;
import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.LoanApplication;
import lombok.Data;

@Data
public class LoanApplicationRequest {
    private Integer amount;
    private Integer termMonths;
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
