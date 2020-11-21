package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.Loan;
import dev.vabalas.loans.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public List<Loan> getLoansForCustomer(Customer customer) {
        return loanRepository.findAllByCustomer(customer);
    }
}
