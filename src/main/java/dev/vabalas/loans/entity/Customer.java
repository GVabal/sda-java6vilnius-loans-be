package dev.vabalas.loans.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String bankAccountNumber;

    protected Customer() {
    }

    public Customer(Long id, String firstName, String lastName, String bankAccountNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bankAccountNumber = bankAccountNumber;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }
}
