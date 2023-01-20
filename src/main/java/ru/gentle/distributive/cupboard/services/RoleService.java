package ru.gentle.distributive.cupboard.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gentle.distributive.cupboard.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
}
