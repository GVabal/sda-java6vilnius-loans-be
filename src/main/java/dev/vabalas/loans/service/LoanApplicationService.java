package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.LoanApplication;
import dev.vabalas.loans.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;

    public LoanApplicationService(LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
    }

    public List<LoanApplication> getAppliedLoansForCustomer(Customer customer) {
        return loanApplicationRepository.findAllByAppliedBy(customer);
    }
}
