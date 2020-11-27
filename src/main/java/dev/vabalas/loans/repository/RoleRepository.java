package dev.vabalas.loans.repository;

import dev.vabalas.loans.entity.Role;
import dev.vabalas.loans.entity.RoleAuthority;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(RoleAuthority name);
}
