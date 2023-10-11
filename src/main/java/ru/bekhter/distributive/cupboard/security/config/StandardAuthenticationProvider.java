package ru.bekhter.distributive.cupboard.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bekhter.distributive.cupboard.security.DatabaseUserDetailsService;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class StandardAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final DatabaseUserDetailsService userService;
    private final BCryptPasswordEncoder bCrypt;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        if (!bCrypt.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            log.error("Bad credentials for {}", userDetails.getUsername());
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        return userService.loadUserByUsername(username);
    }
}
