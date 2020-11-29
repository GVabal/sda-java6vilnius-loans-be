package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.Employee;
import dev.vabalas.loans.entity.Status;
import dev.vabalas.loans.entity.User;
import dev.vabalas.loans.exception.NotFoundException;
import dev.vabalas.loans.exception.UserAlreadyTerminatedException;
import dev.vabalas.loans.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;
    private final UserService userService;

    public List<Employee> findAll() {
        LOGGER.info("findAll()");
        return employeeRepository.findAll();
    }

    public Employee findByEmail(String email) {
        LOGGER.info("findByEmail({})", email);
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Employee with email " + email));
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee with id " + id));
    }

    @Transactional
    public void terminateEmployee(Employee employee) {
        LOGGER.info("terminateEmployee({})", employee);
        User user = userService.findByEmail(employee.getEmail());
        if (user.getStatus().equals(Status.TERMINATED))
            throw new UserAlreadyTerminatedException("Employee already terminated with id " + employee.getId());
        user.setStatus(Status.TERMINATED);
        employee.setStatus(Status.TERMINATED);
    }

    public Employee save(User user) {
        LOGGER.info("save({})", user);
        return employeeRepository.save(new Employee(user.getId(), generatePid(), user.getEmail()));
    }

    private String generatePid() {
        return "E" + (new Random().nextInt(999999) * 1000000 - 100000);
    }
}
