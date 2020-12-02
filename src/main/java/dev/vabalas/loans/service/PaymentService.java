package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.*;
import dev.vabalas.loans.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;

    @Transactional
    public void payBackLoan(BigDecimal amount, Loan loan, Customer customer) {
        LOGGER.info("payBackLoan({},{},{})", amount, loan, customer);
        paymentRepository.save(new Payment(PaymentType.INCOMING, amount, loan, customer));
        BigDecimal newAmountPayed = loan.getAmountPayed().add(amount);
        BigDecimal newAmountToRepay;
        if (newAmountPayed.compareTo(loan.getAmountToRepay()) >= 0) {
            newAmountPayed = loan.getAmountToRepay();
            newAmountToRepay = BigDecimal.ZERO;
            loan.setStatus(Status.TERMINATED);
            loan.getLoanApplication().setStatus(ApplicationStatus.COMPLETED);
        } else
            newAmountToRepay = loan.getAmountToRepay().subtract(amount);

        loan.setAmountPayed(newAmountPayed);
        loan.setAmountToRepay(newAmountToRepay);
    }

    public void payOutLoan(Loan loan, LoanApplication loanApplication) {
        LOGGER.info("payBackLoan({},{})", loan, loanApplication);
        paymentRepository.save(new Payment(
                PaymentType.OUTGOING,
                BigDecimal.valueOf(loanApplication.getAmount()),
                loan,
                loanApplication.getAppliedBy()));
    }
}
