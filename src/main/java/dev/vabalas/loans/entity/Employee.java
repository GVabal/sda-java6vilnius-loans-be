package dev.vabalas.loans.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    private Long id;

    private String pid;
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Employee(Long id, String pid, String email) {
        this.id = id;
        this.pid = pid;
        this.email = email;
        this.status = Status.ACTIVE;
    }
}
