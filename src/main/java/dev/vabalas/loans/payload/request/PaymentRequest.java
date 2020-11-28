package dev.vabalas.loans.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class PaymentRequest {
    @NotNull(message = "Loan id must be set")
    private Long loanId;
    @NotNull(message = "Amount must be set")
    @PositiveOrZero(message = "Amount must be positive")
    private Float amount;

    @JsonCreator
    public PaymentRequest(@JsonProperty("loanId") Long loanId, @JsonProperty("amount") Float amount) {
        this.loanId = loanId;
        this.amount = amount;
    }
}
