package ru.gentle.distributive.cupboard.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.gentle.distributive.cupboard.api.JwtRequest;
import ru.gentle.distributive.cupboard.api.JwtResponse;
import ru.gentle.distributive.cupboard.security.utils.JwtTokenUtil;
import ru.gentle.distributive.cupboard.services.UserService;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1/auth")
public class AuthController {
//    private final UserService userService;
//    private final UserConverter userConverter;
//
//    @PostMapping("/user/set_role")
//    public UserDto setRole(@RequestBody RoleRequest roleRequest) {
//        return userConverter.entityToDto(userService.setRole(roleRequest.getNickname(), roleRequest.getRole()));
//    }


    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        log.info("Auth request: [{}, {}]", request.getUsername(), request.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
