package dev.vabalas.loans.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class PaymentRequest {
    @NotNull(message = "Loan id must be set")
    private Long loanId;
    @NotNull(message = "Amount must be set")
    @PositiveOrZero(message = "Amount must be positive")
    private BigDecimal amount;

    @JsonCreator
    public PaymentRequest(@JsonProperty("loanId") Long loanId, @JsonProperty("amount") BigDecimal amount) {
        this.loanId = loanId;
        this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
    }
}
