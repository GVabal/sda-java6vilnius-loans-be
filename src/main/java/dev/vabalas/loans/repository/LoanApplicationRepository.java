package dev.vabalas.loans.repository;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.entity.LoanApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Long> {
    List<LoanApplication> findAllByAppliedBy(Customer customer);
}
