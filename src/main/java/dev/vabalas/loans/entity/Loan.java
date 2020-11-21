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

    private Integer amountGranted;
    private Float amountToRepay;
    private Integer amountPayed;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Loan(Integer amountGranted, Float amountToRepay, Integer amountPayed, Customer customer) {
        this.amountGranted = amountGranted;
        this.amountToRepay = amountToRepay;
        this.amountPayed = amountPayed;
        this.customer = customer;
    }
}
