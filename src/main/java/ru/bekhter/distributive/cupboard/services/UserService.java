package ru.bekhter.distributive.cupboard.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bekhter.distributive.cupboard.dtos.RoleDto;
import ru.bekhter.distributive.cupboard.entities.Role;
import ru.bekhter.distributive.cupboard.specifications.MyUserSpecifications;
import ru.bekhter.distributive.cupboard.dtos.UserDto;
import ru.bekhter.distributive.cupboard.entities.MyUser;
import ru.bekhter.distributive.cupboard.exceptions.ResourceNotFoundException;
import ru.bekhter.distributive.cupboard.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MyUser setRole(String nickname, String role) {
        MyUser user = userRepository.findByNicknameIgnoreCase(nickname)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с nickname: " + nickname + " не найден!"));
        user.getRoles().add(roleService.findRoleByName(role));
        return userRepository.save(user);
    }

    public Optional<MyUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isBanned(Long id) {
        MyUser user = userRepository.getById(id);
        System.out.println(user.isActive());
        return user.isActive();
    }

    @Transactional
    public void setBan(Long id) {
        MyUser user = userRepository.getById(id);
        System.out.println("1" + user);
        if (isBanned(id)) {
            user.setActive(false);
            userRepository.save(user);
            System.out.println("2" + user);
            return;
        }
        user.setActive(true);
        userRepository.save(user);
        System.out.println("3" + user);
    }

    public Page<MyUser> find(String nickname, Integer page) {
        Specification<MyUser> spec = Specification.where(null);
        if (nickname != null) {
            spec = spec.and(MyUserSpecifications.nicknameLike(nickname));
        }
        return userRepository.findAll(spec, PageRequest.of(page - 1, 15));
    }

    public MyUser createNewUser(UserDto userDto) {
        MyUser user = new MyUser();
        user.setNickname(userDto.getNickname());
        user.setUsername(userDto.getMail());
        user.setFullname(userDto.getFullname());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setMail(userDto.getMail());
        user.setDepartment(userDto.getDepartment());
        user.setRoles(List.of(roleService.findRoleByName("ROLE_USER")));
        user.setActive(true);
        user.setPresent(false);
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<Role> findRolesByUsername(String username) {
        MyUser user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Пользователь с username: " + username + " не найден!"));
        return user.getRoles().stream().toList();
    }
}
