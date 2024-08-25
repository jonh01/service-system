package com.servicesystem.api.domain.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
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
    private int tokenTime;

    @Value("${security.refreshToken.time}")
    private int refreshTime;

    public String generateToken(UserDetailsImpl userDetailsImpl, TokenType tokenType) {

        var expiration = genExpirationDate(tokenType);
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(applicationName)
                    .withSubject(userDetailsImpl.getUsername())
                    .withIssuedAt(new Date())
                    .withExpiresAt(expiration)
                    .sign(algorithm);

        } catch (Exception exception) {
            throw new JWTCreationException("Erro de geração de token");
        }
    }

    public String validateToken(String token) throws AlgorithmMismatchException, SignatureVerificationException, TokenExpiredException, MissingClaimException, IncorrectClaimException, JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
            .withIssuer(applicationName)
            .build()
            .verify(token)
            .getSubject();
    }
    

    private Date genExpirationDate(TokenType tokenType) {

        return tokenType.equals(TokenType.Token) ? 
            new Date((new Date()).getTime() + (tokenTime*60000) )
        : 
            new Date((new Date()).getTime() + (refreshTime*60000) );
    }

    public String getUserNameFromJwtToken(String token) {
        return JWT.decode(token).getSubject();
    }
}
