package dev.vabalas.loans.controller;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.LoanApplication;
import dev.vabalas.loans.entity.User;
import dev.vabalas.loans.payload.response.EmployeeLoanApplicationResponse;
import dev.vabalas.loans.payload.response.UserLoanApplicationResponse;
import dev.vabalas.loans.security.TokenParser;
import dev.vabalas.loans.service.CustomerService;
import dev.vabalas.loans.service.LoanApplicationService;
import dev.vabalas.loans.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/loan-applications")
public class LoanApplicationController {

    private final UserService userService;
    private final CustomerService customerService;
    private final LoanApplicationService loanApplicationService;
    private final TokenParser tokenParser;

    public LoanApplicationController(UserService userService, CustomerService customerService, LoanApplicationService loanApplicationService, TokenParser tokenParser) {
        this.userService = userService;
        this.customerService = customerService;
        this.loanApplicationService = loanApplicationService;
        this.tokenParser = tokenParser;
    }

    @GetMapping("customer")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<UserLoanApplicationResponse> getAppliedLoansByTokenEmail(@RequestHeader(value = "Authorization") String accessToken) {
        // TODO Make new method in TokenParser that combines two methods into one.
        String accessTokenWithNoPrefix = tokenParser.removePrefix(accessToken);
        String email = tokenParser.parseEmail(accessTokenWithNoPrefix);
        // TODO add email field to customer, so no userService would be needed here
        User user = userService.findByEmail(email).get();
        Customer customer = customerService.getCustomerById(user.getId());
        List<LoanApplication> loans = loanApplicationService.getAppliedLoansForCustomer(customer);
        return generateUserLoanApplicationResponse(loans);
    }

    @GetMapping("employee")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public List<EmployeeLoanApplicationResponse> getAllPendingLoans() {
        List<LoanApplication> loans = loanApplicationService.getPendingLoans();
        return generateEmployeeLoanApplicationResponse(loans);
    }

    private List<UserLoanApplicationResponse> generateUserLoanApplicationResponse(List<LoanApplication> loans) {
        return loans.stream()
                .map(UserLoanApplicationResponse::fromLoanApplication)
                .collect(Collectors.toList());
    }

    private List<EmployeeLoanApplicationResponse> generateEmployeeLoanApplicationResponse(List<LoanApplication> loans) {
        return loans.stream()
                .map(EmployeeLoanApplicationResponse::fromLoanApplication)
                .collect(Collectors.toList());
    }
}
