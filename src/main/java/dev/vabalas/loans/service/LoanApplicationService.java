package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.ApplicationStatus;
import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.Employee;
import dev.vabalas.loans.entity.LoanApplication;
import dev.vabalas.loans.enums.ApplicationStatusType;
import dev.vabalas.loans.repository.ApplicationStatusRepository;
import dev.vabalas.loans.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final ApplicationStatusRepository applicationStatusRepository;

    public LoanApplicationService(LoanApplicationRepository loanApplicationRepository, ApplicationStatusRepository applicationStatusRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.applicationStatusRepository = applicationStatusRepository;
    }

    public List<LoanApplication> getAppliedLoansForCustomer(Customer customer) {
        return loanApplicationRepository.findAllByAppliedBy(customer);
    }

    public List<LoanApplication> getPendingLoans() {
        return loanApplicationRepository.findAllByStatus_NameEquals(ApplicationStatusType.PENDING);
    }

    public void approveLoanWithId(Long id, Employee employee) {
        LoanApplication loan = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such loan"));
        loan.setStatus(applicationStatusRepository.findByName(ApplicationStatusType.APPROVED));
        loan.setApprovedBy(employee);
        loanApplicationRepository.save(loan);
    }

    public void rejectLoanWithId(Long id, Employee employee) {
        LoanApplication loan = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such loan"));
        loan.setStatus(applicationStatusRepository.findByName(ApplicationStatusType.REJECTED));
        loan.setApprovedBy(employee);
        loanApplicationRepository.save(loan);
    }
}
