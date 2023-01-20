package ru.gentle.distributive.cupboard.converters;

import ru.gentle.distributive.cupboard.dtos.UserDto;
import ru.gentle.distributive.cupboard.entities.MyUser;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class UserConverter {
    //из user в dto
    public UserDto entityToDto(MyUser user){
        return new UserDto(
                user.getId(),
                user.getNickname(),
                user.getPassword(),
                user.getMail(),
                user.getDepartment(),
                user.getUsername(),
                user.getSurname(),
                user.getUpdatedAt().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
        );
    }
}
