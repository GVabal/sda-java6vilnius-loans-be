package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.*;
import dev.vabalas.loans.exception.NotFoundException;
import dev.vabalas.loans.repository.LoanApplicationRepository;
import dev.vabalas.loans.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanService.class);
    private final LoanRepository loanRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final PaymentService paymentService;

    public Loan getLoan(Long id) {
        LOGGER.info("getLoan({})", id);
        return loanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loan with id " + id));
    }

    public List<Loan> getActiveLoansForCustomer(Customer customer) {
        LOGGER.info("getActiveLoansForCustomer({})", customer);
        return loanRepository.findAllByCustomerAndStatusEquals(customer, Status.ACTIVE);
    }

    @Transactional
    public void grantLoan(LoanApplication loanApplication) {
        LOGGER.info("grantLoan({})", loanApplication);
        Loan loan = loanRepository.save(new Loan(
                calculateAmountToRepay(loanApplication),
                loanApplication.getAppliedBy(),
                loanApplication
        ));
        loanApplication.setStatus(ApplicationStatus.TAKEN);
        loanApplicationRepository.save(loanApplication);
        paymentService.payOutLoan(loan, loanApplication);
    }

    private BigDecimal calculateAmountToRepay(LoanApplication loanApplication) {
        Float extraAmount = ((float) loanApplication.getTermMonths() / 12)
                * (loanApplication.getInterestRatePerYear() / 100)
                * loanApplication.getAmount();
        return BigDecimal.valueOf(loanApplication.getAmount() + extraAmount)
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}
