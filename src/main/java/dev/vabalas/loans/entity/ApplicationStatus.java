package dev.vabalas.loans.entity;

import dev.vabalas.loans.enums.ApplicationStatusType;

import javax.persistence.*;

@Entity
public class ApplicationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplicationStatusType name;

    protected ApplicationStatus() {
    }

    public ApplicationStatus(ApplicationStatusType name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public ApplicationStatusType getName() {
        return name;
    }
}
