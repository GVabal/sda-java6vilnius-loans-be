package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.Role;
import dev.vabalas.loans.enums.RoleAuthority;
import dev.vabalas.loans.exception.NotFoundException;
import dev.vabalas.loans.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role mustFindByName(RoleAuthority name) {
        return roleRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Role name %s", name)));
    }
}
