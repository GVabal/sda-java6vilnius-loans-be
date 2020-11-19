package dev.vabalas.loans.repository;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.LoanApplication;
import dev.vabalas.loans.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findAllByAppliedBy(Customer customer);
    List<LoanApplication> findAllByStatus(ApplicationStatus status);
}
