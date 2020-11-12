package dev.vabalas.loans.entity;


import dev.vabalas.loans.enums.RoleAuthority;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private RoleAuthority name;

    protected Role() {
    }

    public Role(RoleAuthority name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public RoleAuthority getName() {
        return name;
    }
}
