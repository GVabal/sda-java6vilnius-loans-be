package dev.vabalas.loans.controller;

import dev.vabalas.loans.entity.Employee;
import dev.vabalas.loans.entity.Role;
import dev.vabalas.loans.entity.RoleAuthority;
import dev.vabalas.loans.entity.User;
import dev.vabalas.loans.exception.UserExistsException;
import dev.vabalas.loans.payload.request.UserCreateRequest;
import dev.vabalas.loans.service.EmployeeService;
import dev.vabalas.loans.service.RoleService;
import dev.vabalas.loans.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @PostMapping("register")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Employee addEmployee(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        if (userService.existsByEmail(userCreateRequest.getEmail())) {
            throw new UserExistsException(
                    String.format("User with email %s already exists", userCreateRequest.getEmail()));
        }
        Role role = roleService.mustFindByName(RoleAuthority.ROLE_EMPLOYEE);
        User user =
                userCreateRequest.asUser(passwordEncoder.encode(userCreateRequest.getPassword()), role);
        userService.save(user);
        User registeredUser = (User) userDetailsService.loadUserByUsername(userCreateRequest.getEmail());
        return employeeService.save(registeredUser);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void terminateEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        employeeService.terminateEmployee(employee);
    }
}
