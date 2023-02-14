package ru.gentle.distributive.cupboard.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gentle.distributive.cupboard.dtos.UserDto;
import ru.gentle.distributive.cupboard.entities.MyUser;
import ru.gentle.distributive.cupboard.entities.Role;
import ru.gentle.distributive.cupboard.exceptions.ResourceNotFoundException;
import ru.gentle.distributive.cupboard.repositories.UserRepository;
import ru.gentle.distributive.cupboard.specifications.MyUserSpecifications;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public MyUser setRole(String nickname, String role) {
        MyUser user = userRepository.findByNicknameIgnoreCase(nickname)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с nickname: " + nickname + " не найден!"));
        user.getRoles().add(roleService.findRoleByName(role));
        return userRepository.save(user);
    }

    public Optional<MyUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNameRole())).collect(Collectors.toList());
    }

    public boolean isBanned(Long id) {
        MyUser user = userRepository.getById(id);
        return user.isActive();
    }

    public void setBan(Long id) {
        MyUser user = userRepository.getById(id);
        if (isBanned(id)) {
            user.setActive(true);
            userRepository.save(user);
        } else {
            user.setActive(false);
            userRepository.save(user);
        }
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
        user.setUsername(userDto.getUsername());
        user.setSurname(userDto.getSurname());
        user.setPassword(userDto.getPassword());
        user.setMail(userDto.getMail());
        user.setDepartment(userDto.getDepartment());
        user.setRoles(List.of(roleService.getUserRole()));
        user.setActive(true);
        user.setPresent(false);
        userRepository.save(user);
        return user;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
