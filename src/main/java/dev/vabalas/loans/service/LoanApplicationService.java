package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.ApplicationStatus;
import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.Employee;
import dev.vabalas.loans.entity.LoanApplication;
import dev.vabalas.loans.exception.NotFoundException;
import dev.vabalas.loans.repository.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanApplicationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanApplicationService.class);
    private final LoanApplicationRepository loanApplicationRepository;

    public LoanApplication getLoanApplication(Long id) {
        LOGGER.info("getLoanApplication({})", id);
        return loanApplicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loan application with id " + id));
    }

    public List<LoanApplication> getAppliedLoansForCustomer(Customer customer) {
        LOGGER.info("getAppliedLoansForCustomer({})", customer);
        return loanApplicationRepository.findAllByAppliedBy(customer);
    }

    public List<LoanApplication> getPendingLoans() {
        LOGGER.info("getPendingLoans()");
        return loanApplicationRepository.findAllByStatus(ApplicationStatus.PENDING);
    }

    public LoanApplication addNew(LoanApplication loanApplication) {
        LOGGER.info("addNew({})", loanApplication);
        return loanApplicationRepository.save(loanApplication);
    }

    public void approveLoanWithId(Long id, Employee employee) {
        LOGGER.info("approveLoanWithId({},{})", id, employee);
        LoanApplication loan = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loan application with id " + id));
        loan.setStatus(ApplicationStatus.APPROVED);
        loan.setApprovedBy(employee);
        loanApplicationRepository.save(loan);
    }

    public void rejectLoanWithId(Long id, Employee employee) {
        LOGGER.info("rejectLoanWithId({},{})", id, employee);
        LoanApplication loan = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loan application with id " + id));
        loan.setStatus(ApplicationStatus.REJECTED);
        loan.setApprovedBy(employee);
        loanApplicationRepository.save(loan);
    }
}
