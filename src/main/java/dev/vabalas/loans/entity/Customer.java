package dev.vabalas.loans.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String bankAccountNumber;
}
