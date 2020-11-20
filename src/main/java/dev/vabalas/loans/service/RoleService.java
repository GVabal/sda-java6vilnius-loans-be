package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.Role;
import dev.vabalas.loans.entity.RoleAuthority;
import dev.vabalas.loans.exception.NotFoundException;
import dev.vabalas.loans.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public Role mustFindByName(RoleAuthority name) {
        return roleRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Role " + name));
    }
}
