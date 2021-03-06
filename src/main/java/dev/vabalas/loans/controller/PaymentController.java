package dev.vabalas.loans.controller;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.Loan;
import dev.vabalas.loans.payload.request.PaymentRequest;
import dev.vabalas.loans.security.TokenParser;
import dev.vabalas.loans.service.CustomerService;
import dev.vabalas.loans.service.LoanService;
import dev.vabalas.loans.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/payments")
public class PaymentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;
    private final CustomerService customerService;
    private final LoanService loanService;
    private final TokenParser tokenParser;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public void payBackLoan(@RequestHeader(value = "Authorization") String accessToken,
                            @RequestBody @Valid PaymentRequest paymentRequest) {
        LOGGER.info("POST /api/payments");
        String email = tokenParser.extractEmailString(accessToken);
        Customer customer = customerService.findByEmail(email);
        Loan loan = loanService.getLoan(paymentRequest.getLoanId());
        paymentService.payBackLoan(paymentRequest.getAmount(), loan, customer);
    }
}
