package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.Employee;
import dev.vabalas.loans.entity.LoanApplication;
import dev.vabalas.loans.entity.ApplicationStatus;
import dev.vabalas.loans.exception.NotFoundException;
import dev.vabalas.loans.repository.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;

    public List<LoanApplication> getAppliedLoansForCustomer(Customer customer) {
        return loanApplicationRepository.findAllByAppliedBy(customer);
    }

    public List<LoanApplication> getPendingLoans() {
        return loanApplicationRepository.findAllByStatus(ApplicationStatus.PENDING);
    }

    public void approveLoanWithId(Long id, Employee employee) {
        LoanApplication loan = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loan application with id " + id));
        loan.setStatus(ApplicationStatus.APPROVED);
        loan.setApprovedBy(employee);
        loanApplicationRepository.save(loan);
    }

    public void rejectLoanWithId(Long id, Employee employee) {
        LoanApplication loan = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loan application with id " + id));
        loan.setStatus(ApplicationStatus.REJECTED);
        loan.setApprovedBy(employee);
        loanApplicationRepository.save(loan);
    }
}
