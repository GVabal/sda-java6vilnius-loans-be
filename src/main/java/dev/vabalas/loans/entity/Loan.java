package dev.vabalas.loans.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float amountToRepay;
    private Float amountPayed;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Loan( Float amountToRepay, Float amountPayed, Customer customer) {
        this.amountToRepay = amountToRepay;
        this.amountPayed = amountPayed;
        this.customer = customer;
        this.status = Status.ACTIVE;
    }
}
