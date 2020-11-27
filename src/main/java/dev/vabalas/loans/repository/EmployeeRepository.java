package dev.vabalas.loans.repository;

import dev.vabalas.loans.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    List<Employee> findAll();
}
