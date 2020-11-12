package dev.vabalas.loans.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleAuthority implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_EMPLOYEE;

    @Override
    public String getAuthority() {
        return toString();
    }
}
