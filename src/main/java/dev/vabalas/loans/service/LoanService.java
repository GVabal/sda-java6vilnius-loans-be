package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.ApplicationStatus;
import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.Loan;
import dev.vabalas.loans.entity.LoanApplication;
import dev.vabalas.loans.repository.LoanApplicationRepository;
import dev.vabalas.loans.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanApplicationRepository loanApplicationRepository;

    public List<Loan> getLoansForCustomer(Customer customer) {
        return loanRepository.findAllByCustomer(customer);
    }

    @Transactional
    public void grantLoan(LoanApplication loanApplication) {
        loanRepository.save(new Loan(
                loanApplication.getAmount(),
                calculateAmountToRepay(loanApplication),
                0,
                loanApplication.getAppliedBy()
                ));
        loanApplication.setStatus(ApplicationStatus.TAKEN);
        loanApplicationRepository.save(loanApplication);
    }

    private Float calculateAmountToRepay(LoanApplication loanApplication) {
        Float extraAmount = (loanApplication.getTermMonths() / 12) * (loanApplication.getInterestRatePerYear() / 100) * loanApplication.getAmount();
        return loanApplication.getAmount() + extraAmount;
    }
}
