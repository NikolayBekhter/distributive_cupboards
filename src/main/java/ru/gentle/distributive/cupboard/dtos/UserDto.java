package ru.gentle.distributive.cupboard.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String mail;
    private String username;
    private String surname;
    private String department;
    private String updatedAt;
}
