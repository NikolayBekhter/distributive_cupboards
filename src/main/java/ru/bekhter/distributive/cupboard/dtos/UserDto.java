package ru.bekhter.distributive.cupboard.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String mail;
    private String fullname;
    private String department;
    private boolean isActive;
    private boolean isPresent;
}
