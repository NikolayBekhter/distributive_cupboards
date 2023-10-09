package ru.bekhter.distributive.cupboard.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final JwtProperties properties;

    public String generateJwtToken(UserDetails user) {
        String username = user.getUsername();
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> claims = new HashMap<>(Map.of("authority", authorities));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + properties.getLifetime()))
                .signWith(SignatureAlgorithm.HS256, properties.getSecret())
                .compact();
    }

    public String getUsername(String bearerTokenValue) {
        return parse(bearerTokenValue).getSubject();
    }

    public List<GrantedAuthority> getAuthority(String bearerTokenValue) {
        List<String> authority = (List<String>) parse(bearerTokenValue).get("authority");

        return authority.stream()
                .map(SimpleGrantedAuthority::new)
                .map(it -> (GrantedAuthority) it)
                .toList();
    }

    private Claims parse(String value) {
        return Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(value)
                .getBody();
    }
}
