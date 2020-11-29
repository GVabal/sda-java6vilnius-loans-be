package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.Customer;
import dev.vabalas.loans.exception.NotFoundException;
import dev.vabalas.loans.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;

    public Customer findByEmail(String email) {
        LOGGER.info("findByEmail({})", email);
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Customer with email " + email));
    }

    public void save(Customer customer) {
        LOGGER.info("save({})", customer);
        customerRepository.save(customer);
    }
}
