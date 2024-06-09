package com.servicesystem.api.domain.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.servicesystem.api.domain.exceptions.JWTCreationException;
import com.servicesystem.api.domain.models.enums.TokenType;
import com.servicesystem.api.domain.models.users.UserDetailsImpl;

@Component
public class JwtUtils {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${security.token.secret}")
    private String secret;

    @Value("${security.token.time}")
    private Long tokenTime;

    @Value("${security.refreshToken.time}")
    private Long refreshTime;

    public String generateToken(UserDetailsImpl userDetailsImpl, TokenType tokenType) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(applicationName)
                    .withSubject(userDetailsImpl.getUsername())
                    .withExpiresAt(genExpirationDate(tokenType))
                    .sign(algorithm);

        } catch (Exception exception) {
            throw new JWTCreationException("Erro de geração de token");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(applicationName)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception exception) {
            return "-1";
        }
    }

    private Instant genExpirationDate(TokenType tokenType) {

        return tokenType.equals(TokenType.Token) ? 
            LocalDateTime.now().plusMinutes(tokenTime).toInstant(ZoneOffset.of("-03:00"))
        : 
            LocalDateTime.now().plusMinutes(refreshTime).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getUserNameFromJwtToken(String token) {
        return JWT.decode(token).getSubject();
    }
}
