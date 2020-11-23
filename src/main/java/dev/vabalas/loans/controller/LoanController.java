package dev.vabalas.loans.controller;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.Loan;
import dev.vabalas.loans.security.TokenParser;
import dev.vabalas.loans.service.CustomerService;
import dev.vabalas.loans.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/loans")
public class LoanController {

    private final LoanService loanService;
    private final CustomerService customerService;
    private final TokenParser tokenParser;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public List<Loan> getActiveLoansByTokenEmail(@RequestHeader(value = "Authorization") String accessToken) {
        String email = tokenParser.extractEmailString(accessToken);
        Customer customer = customerService.findByEmail(email);
        List<Loan> loans = loanService.getActiveLoansForCustomer(customer);
        return loans;
    }
}
