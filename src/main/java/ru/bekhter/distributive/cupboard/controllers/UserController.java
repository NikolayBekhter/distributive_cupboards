package ru.bekhter.distributive.cupboard.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.bekhter.distributive.cupboard.api.RoleRequest;
import ru.bekhter.distributive.cupboard.converters.RoleConverter;
import ru.bekhter.distributive.cupboard.converters.UserConverter;
import ru.bekhter.distributive.cupboard.dtos.RoleDto;
import ru.bekhter.distributive.cupboard.dtos.UserDto;
import ru.bekhter.distributive.cupboard.entities.MyUser;
import ru.bekhter.distributive.cupboard.services.UserService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;
    private final RoleConverter roleConverter;


    @GetMapping("/ban/{id}")
    public void banUser(@PathVariable Long id) {
        userService.setBan(id);
    }

    @GetMapping
    public Page<UserDto> getUsers(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "nickname_part", required = false) String nickname
    ) {
        if (page < 1) {
            page = 1;
        }
        return userService.find(nickname, page).map(
                userConverter::entityToDto
        );
    }

    @PostMapping
    public UserDto createNewUser(@RequestBody UserDto userDto) {
        return userConverter.entityToDto(userService.createNewUser(userDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PostMapping("/user/set_role")
    public UserDto setRole(@RequestBody RoleRequest roleRequest) {
        return userConverter.entityToDto(userService.setRole(roleRequest.getNickname(), roleRequest.getRole()));
    }

    @GetMapping("/get_roles/{username}")
    public List<RoleDto> getRoles(@PathVariable String username) {
        return userService.findRolesByUsername(username).stream()
                .map(roleConverter::entityToDto)
                .collect(Collectors.toList());
    }
}
