package ru.gentle.distributive.cupboard.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String userLogin;
    private String userPassword;
    private String mail;
    private String userName;
    private String userSurname;
    private String department;
    private String updatedAt;
}
