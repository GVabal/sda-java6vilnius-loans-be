package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.exception.NotFoundException;
import dev.vabalas.loans.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Customer with email " + email));
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
