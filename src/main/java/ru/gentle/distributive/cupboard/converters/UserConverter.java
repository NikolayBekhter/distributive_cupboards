package ru.gentle.distributive.cupboard.converters;

import org.springframework.stereotype.Service;
import ru.gentle.distributive.cupboard.dtos.UserDto;
import ru.gentle.distributive.cupboard.entities.MyUser;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class UserConverter {
    //из user в dto
    public UserDto entityToDto(MyUser user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setNickname(user.getNickname());
        userDto.setPassword(user.getPassword());
        userDto.setMail(user.getMail());
        userDto.setDepartment(user.getDepartment());
        userDto.setSurname(user.getSurname());
        userDto.setUsername(user.getUsername());
        userDto.setUpdatedAt(user.getUpdatedAt().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        userDto.setRoles(user.getRoles().stream().toList());

        return userDto;
    }
}
