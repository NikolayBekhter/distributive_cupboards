package ru.gentle.distributive.cupboard.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gentle.distributive.cupboard.entities.Role;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String nickname;
    private String password;
    private String mail;
    private String username;
    private String surname;
    private String department;
    private String updatedAt;
    private List<Role> roles;
}
