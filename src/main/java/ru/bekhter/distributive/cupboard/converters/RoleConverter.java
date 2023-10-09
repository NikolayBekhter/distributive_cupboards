package ru.bekhter.distributive.cupboard.converters;

import org.springframework.stereotype.Service;
import ru.bekhter.distributive.cupboard.dtos.RoleDto;
import ru.bekhter.distributive.cupboard.entities.Role;

@Service
public class RoleConverter {
    public RoleDto entityToDto(Role role) {
        RoleDto dto = new RoleDto();
        dto.setName(role.getNameRole());
        return dto;
    }
}
