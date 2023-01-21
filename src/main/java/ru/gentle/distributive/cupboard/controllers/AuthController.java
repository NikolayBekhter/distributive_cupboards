package ru.gentle.distributive.cupboard.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gentle.distributive.cupboard.api.RoleRequest;
import ru.gentle.distributive.cupboard.converters.UserConverter;
import ru.gentle.distributive.cupboard.dtos.UserDto;
import ru.gentle.distributive.cupboard.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping("/user/set_role")
    public UserDto setRole(@RequestBody RoleRequest roleRequest) {
        return userConverter.entityToDto(userService.setRole(roleRequest.getNickname(), roleRequest.getRole()));
    }
}
