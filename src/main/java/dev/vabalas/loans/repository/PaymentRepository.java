package dev.vabalas.loans.repository;

import dev.vabalas.loans.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
