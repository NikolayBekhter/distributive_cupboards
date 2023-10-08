package ru.bekhter.distributive.cupboard.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.bekhter.distributive.cupboard.api.JwtRequest;
import ru.bekhter.distributive.cupboard.api.JwtResponse;
import ru.bekhter.distributive.cupboard.security.utils.JwtTokenUtil;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        try {
            System.out.println(1);
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            System.out.println(2);

            UserDetails user = (UserDetails) authenticate.getPrincipal();
            String token = jwtTokenUtil.generateJwtToken(user);
            log.info("Пользователь {} авторизовался!", request.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (BadCredentialsException e) {
            System.out.println(3);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
