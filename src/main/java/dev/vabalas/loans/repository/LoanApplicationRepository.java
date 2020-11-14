package dev.vabalas.loans.repository;

import dev.vabalas.loans.entity.LoanApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Long> {
}
