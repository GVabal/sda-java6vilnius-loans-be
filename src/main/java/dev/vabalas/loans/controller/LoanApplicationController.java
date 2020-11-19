package dev.vabalas.loans.controller;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.Employee;
import dev.vabalas.loans.entity.LoanApplication;
import dev.vabalas.loans.payload.response.EmployeeLoanApplicationResponse;
import dev.vabalas.loans.payload.response.CustomerLoanApplicationResponse;
import dev.vabalas.loans.security.TokenParser;
import dev.vabalas.loans.service.CustomerService;
import dev.vabalas.loans.service.EmployeeService;
import dev.vabalas.loans.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/loan-applications")
public class LoanApplicationController {

    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final LoanApplicationService loanApplicationService;
    private final TokenParser tokenParser;

    @GetMapping("customer")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public List<CustomerLoanApplicationResponse> getAppliedLoansByTokenEmail(@RequestHeader(value = "Authorization") String accessToken) {
        String email = tokenParser.extractEmailString(accessToken);
        Customer customer = customerService.findByEmail(email);
        List<LoanApplication> loans = loanApplicationService.getAppliedLoansForCustomer(customer);
        return generateCustomerLoanApplicationResponse(loans);
    }

    @GetMapping("employee")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public List<EmployeeLoanApplicationResponse> getAllPendingLoans() {
        List<LoanApplication> loans = loanApplicationService.getPendingLoans();
        return generateEmployeeLoanApplicationResponse(loans);
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("employee/approve/{id}")
    public void approveLoanWithId(@PathVariable Long id, @RequestHeader(value = "Authorization") String accessToken) {
        String email = tokenParser.extractEmailString(accessToken);
        Employee employee = employeeService.findByEmail(email);
        loanApplicationService.approveLoanWithId(id, employee);
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("employee/reject/{id}")
    public void rejectLoanWithId(@PathVariable Long id, @RequestHeader(value = "Authorization") String accessToken) {
        String email = tokenParser.extractEmailString(accessToken);
        Employee employee = employeeService.findByEmail(email);
        loanApplicationService.rejectLoanWithId(id, employee);
    }

    private List<CustomerLoanApplicationResponse> generateCustomerLoanApplicationResponse(List<LoanApplication> loans) {
        return loans.stream()
                .map(CustomerLoanApplicationResponse::fromLoanApplication)
                .collect(Collectors.toList());
    }

    private List<EmployeeLoanApplicationResponse> generateEmployeeLoanApplicationResponse(List<LoanApplication> loans) {
        return loans.stream()
                .map(EmployeeLoanApplicationResponse::fromLoanApplication)
                .collect(Collectors.toList());
    }
}
