package dev.vabalas.loans.repository;

import dev.vabalas.loans.entity.Role;
import dev.vabalas.loans.entity.RoleAuthority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(RoleAuthority name);
}
