package dev.vabalas.loans.repository;

import dev.vabalas.loans.entity.ApplicationStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationStatusRepository extends CrudRepository<ApplicationStatus, Long> {
}
