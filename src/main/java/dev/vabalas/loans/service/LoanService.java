package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.*;
import dev.vabalas.loans.exception.NotFoundException;
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
    private final PaymentService paymentService;

    public Loan getLoan(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loan with id " + id));
    }

    public List<Loan> getActiveLoansForCustomer(Customer customer) {
        return loanRepository.findAllByCustomerAndStatusEquals(customer, Status.ACTIVE);
    }

    @Transactional
    public void grantLoan(LoanApplication loanApplication) {
        Loan loan = loanRepository.save(new Loan(
                calculateAmountToRepay(loanApplication),
                0F,
                loanApplication.getAppliedBy(),
                loanApplication
        ));
        loanApplication.setStatus(ApplicationStatus.TAKEN);
        loanApplicationRepository.save(loanApplication);
        paymentService.payOutLoan(loan, loanApplication);
    }

    private Float calculateAmountToRepay(LoanApplication loanApplication) {
        Float extraAmount = ((float) loanApplication.getTermMonths() / 12)
                * (loanApplication.getInterestRatePerYear() / 100)
                * loanApplication.getAmount();
        return loanApplication.getAmount() + extraAmount;
    }
}
