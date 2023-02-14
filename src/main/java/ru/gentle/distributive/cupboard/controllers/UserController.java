package ru.gentle.distributive.cupboard.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gentle.distributive.cupboard.converters.UserConverter;
import ru.gentle.distributive.cupboard.dtos.UserDto;
import ru.gentle.distributive.cupboard.entities.MyUser;
import ru.gentle.distributive.cupboard.services.UserService;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;


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
        MyUser user = userService.createNewUser(userDto);
        return userConverter.entityToDto(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
