package dev.vabalas.loans.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentRequest {
    @NotNull
    private Long loanId;
    @NotNull
    private Float amount;

    @JsonCreator
    public PaymentRequest(@JsonProperty("loanId") Long loanId,@JsonProperty("amount") Float amount) {
        this.loanId = loanId;
        this.amount = amount;
    }
}
