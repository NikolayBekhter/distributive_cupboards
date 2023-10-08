package ru.bekhter.distributive.cupboard.converters;

import org.springframework.stereotype.Service;
import ru.bekhter.distributive.cupboard.entities.MyUser;
import ru.bekhter.distributive.cupboard.dtos.UserDto;

@Service
public class UserConverter {
    //из user в dto
    public UserDto entityToDto(MyUser user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setNickname(user.getNickname());
        userDto.setActive(user.isActive());
        userDto.setPresent(user.isPresent());
        userDto.setDepartment(user.getDepartment());
        userDto.setFullname(user.getFullname());
        userDto.setUsername(user.getUsername());

        return userDto;
    }
}
