package ru.bekhter.distributive.cupboard.security.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(JwtProperties.PREFIX)
public class JwtProperties {

    public static final String PREFIX = "jwt";

    private String secret;

    private Long lifetime;

}
