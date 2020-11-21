package dev.vabalas.loans.repository;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findAllByCustomer(Customer customer);
}
