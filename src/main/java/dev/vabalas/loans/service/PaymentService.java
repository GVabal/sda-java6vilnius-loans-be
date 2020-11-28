package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.*;
import dev.vabalas.loans.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public void payBackLoan(Float amount, Loan loan, Customer customer) {
        paymentRepository.save(new Payment(PaymentType.INCOMING, amount, loan, customer));
        Float newAmountPayed = loan.getAmountPayed() + amount;
        float newAmountToRepay;
        if (newAmountPayed >= loan.getAmountToRepay()) {
            newAmountPayed = loan.getAmountToRepay();
            newAmountToRepay = 0F;
            loan.setStatus(Status.TERMINATED);
            loan.getLoanApplication().setStatus(ApplicationStatus.COMPLETED);
        } else
            newAmountToRepay = loan.getAmountToRepay() - amount;

        loan.setAmountPayed(newAmountPayed);
        loan.setAmountToRepay(newAmountToRepay);
    }

    public void payOutLoan(Loan loan, LoanApplication loanApplication) {
        paymentRepository.save(new Payment(
                PaymentType.OUTGOING,
                Float.parseFloat(loanApplication.getAmount().toString()),
                loan,
                loanApplication.getAppliedBy()));
    }
}
