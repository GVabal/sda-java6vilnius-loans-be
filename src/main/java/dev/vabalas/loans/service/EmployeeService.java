package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.Employee;
import dev.vabalas.loans.exception.NotFoundException;
import dev.vabalas.loans.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Employee with email " + email));
    }
}
