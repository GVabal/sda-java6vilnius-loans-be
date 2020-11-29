package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.Role;
import dev.vabalas.loans.entity.RoleAuthority;
import dev.vabalas.loans.exception.NotFoundException;
import dev.vabalas.loans.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
    private final RoleRepository roleRepository;

    public Role mustFindByName(RoleAuthority name) {
        LOGGER.info("mustFindByName({})", name);
        return roleRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Role " + name));
    }
}
