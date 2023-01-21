package ru.gentle.distributive.cupboard.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gentle.distributive.cupboard.entities.MyUser;
import ru.gentle.distributive.cupboard.exceptions.ResourceNotFoundException;
import ru.gentle.distributive.cupboard.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public MyUser setRole(String nickname, String role) {
        MyUser user = userRepository.findByNicknameIgnoreCase(nickname)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с nickname: " + nickname + " не найден!"));
        user.getRoles().add(roleService.findRoleByName(role));
        return userRepository.save(user);
    }
}
