package ru.gentle.distributive.cupboard.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gentle.distributive.cupboard.entities.Role;
import ru.gentle.distributive.cupboard.exceptions.ResourceNotFoundException;
import ru.gentle.distributive.cupboard.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findRoleByName(String nameRole) {
        return roleRepository.findByNameRole(nameRole)
                .orElseThrow(() -> new ResourceNotFoundException("Роль не найдена."));
    }
    public Role getUserRole() {
        return roleRepository.findByNameRole("ROLE_USER").get();
    }
}
