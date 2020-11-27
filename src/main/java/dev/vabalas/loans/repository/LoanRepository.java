package dev.vabalas.loans.repository;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.Loan;
import dev.vabalas.loans.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findAllByCustomerAndStatusEquals(Customer customer, Status status);
}
