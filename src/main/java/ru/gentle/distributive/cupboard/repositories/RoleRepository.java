package ru.gentle.distributive.cupboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gentle.distributive.cupboard.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
