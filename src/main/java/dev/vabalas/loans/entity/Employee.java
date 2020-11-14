package dev.vabalas.loans.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    private Long id;

    private String pid;

    protected Employee() {
    }

    public Employee(Long id, String pid) {
        this.id = id;
        this.pid = pid;
    }

    public Long getId() {
        return id;
    }

    public String getPid() {
        return pid;
    }
}
