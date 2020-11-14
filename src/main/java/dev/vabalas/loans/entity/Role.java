package dev.vabalas.loans.entity;


import dev.vabalas.loans.enums.RoleAuthority;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleAuthority name;

    protected Role() {
    }

    public Role(RoleAuthority name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public RoleAuthority getName() {
        return name;
    }
}
