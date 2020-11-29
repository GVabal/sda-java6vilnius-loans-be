package dev.vabalas.loans.controller;

import dev.vabalas.loans.entity.ApplicationStatus;
import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.Employee;
import dev.vabalas.loans.entity.LoanApplication;
import dev.vabalas.loans.exception.CannotGrantLoanException;
import dev.vabalas.loans.payload.request.LoanApplicationRequest;
import dev.vabalas.loans.payload.response.CustomerLoanApplicationResponse;
import dev.vabalas.loans.payload.response.EmployeeLoanApplicationResponse;
import dev.vabalas.loans.security.TokenParser;
import dev.vabalas.loans.service.CustomerService;
import dev.vabalas.loans.service.EmployeeService;
import dev.vabalas.loans.service.LoanApplicationService;
import dev.vabalas.loans.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/loan-applications")
public class LoanApplicationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanApplicationController.class);
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final LoanApplicationService loanApplicationService;
    private final LoanService loanService;
    private final TokenParser tokenParser;

    @GetMapping("customer")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public List<CustomerLoanApplicationResponse> getAppliedLoansByTokenEmail(
            @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("GET /api/loan-applications/customer");
        String email = tokenParser.extractEmailString(accessToken);
        Customer customer = customerService.findByEmail(email);
        List<LoanApplication> loans = loanApplicationService.getAppliedLoansForCustomer(customer);
        return generateCustomerLoanApplicationResponse(loans);
    }

    @GetMapping("employee")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public List<EmployeeLoanApplicationResponse> getAllPendingLoans() {
        LOGGER.info("GET /api/loan-applications/employee");
        List<LoanApplication> loans = loanApplicationService.getPendingLoans();
        return generateEmployeeLoanApplicationResponse(loans);
    }

    @PostMapping("customer")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public CustomerLoanApplicationResponse addLoanApplication(
            @RequestHeader(value = "Authorization") String accessToken,
            @RequestBody @Valid LoanApplicationRequest loanApplicationRequest) {
        LOGGER.info("POST /api/loan-applications/customer");
        String email = tokenParser.extractEmailString(accessToken);
        Customer customer = customerService.findByEmail(email);
        return generateCustomerLoanApplicationResponse(
                loanApplicationService.addNew(loanApplicationRequest.toLoanApplication(customer)));
    }

    @PostMapping("customer/{id}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public void takeLoanWithId(@RequestHeader(value = "Authorization") String accessToken, @PathVariable Long id) {
        LOGGER.info("POST /api/loan-applications/customer/{}", id);
        String email = tokenParser.extractEmailString(accessToken);
        Customer customer = customerService.findByEmail(email);
        LoanApplication loanApplication = loanApplicationService.getLoanApplication(id);
        if (loanApplication.getAppliedBy() == customer
                && loanApplication.getStatus().equals(ApplicationStatus.APPROVED)) {
            loanService.grantLoan(loanApplication);
        } else {
            throw new CannotGrantLoanException("Cannot grant loan");
        }
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("employee/approve/{id}")
    public void approveLoanWithId(@PathVariable Long id, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("POST /api/loan-applications/employee/approve/{}", id);
        String email = tokenParser.extractEmailString(accessToken);
        Employee employee = employeeService.findByEmail(email);
        loanApplicationService.approveLoanWithId(id, employee);
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("employee/reject/{id}")
    public void rejectLoanWithId(@PathVariable Long id, @RequestHeader(value = "Authorization") String accessToken) {
        LOGGER.info("POST /api/loan-applications/employee/reject/{}", id);
        String email = tokenParser.extractEmailString(accessToken);
        Employee employee = employeeService.findByEmail(email);
        loanApplicationService.rejectLoanWithId(id, employee);
    }

    private List<CustomerLoanApplicationResponse> generateCustomerLoanApplicationResponse(List<LoanApplication> loans) {
        return loans.stream()
                .map(CustomerLoanApplicationResponse::fromLoanApplication)
                .collect(Collectors.toList());
    }

    private CustomerLoanApplicationResponse generateCustomerLoanApplicationResponse(LoanApplication loan) {
        return CustomerLoanApplicationResponse.fromLoanApplication(loan);
    }

    private List<EmployeeLoanApplicationResponse> generateEmployeeLoanApplicationResponse(List<LoanApplication> loans) {
        return loans.stream()
                .map(EmployeeLoanApplicationResponse::fromLoanApplication)
                .collect(Collectors.toList());
    }
}
