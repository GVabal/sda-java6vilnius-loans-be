package dev.vabalas.loans.controller;

import dev.vabalas.loans.entity.Employee;
import dev.vabalas.loans.entity.User;
import dev.vabalas.loans.payload.request.UserCreateRequest;
import dev.vabalas.loans.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final AuthController authController;
    private final UserDetailsService userDetailsService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @PostMapping("register")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Employee addEmployee(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        authController.registerEmployee(userCreateRequest);
        User user = (User) userDetailsService.loadUserByUsername(userCreateRequest.getEmail());
        return employeeService.saveUserAsEmployee(user);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void terminateEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        employeeService.terminateEmployee(employee);
    }
}
