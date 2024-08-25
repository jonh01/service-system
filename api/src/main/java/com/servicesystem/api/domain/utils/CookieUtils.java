package com.servicesystem.api.domain.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.servicesystem.api.application.services.RefreshTokenService;
import com.servicesystem.api.domain.models.enums.TokenType;
import com.servicesystem.api.domain.models.users.UserDetailsImpl;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.Cookie;

@Component
public class CookieUtils {

    private static final String jwtCookieName = "jwt-cookie";
    private static final String refreshTokenCookieName = "refreshToken-cookie";

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    public ResponseCookie addJwtCookie(UserDetailsImpl user) {
        String jwt = jwtUtils.generateToken(user, TokenType.Token);
        return generateCookie(jwtCookieName, jwt, "/api"); 
    }

    public ResponseCookie addRefreshTokenCookie(String email) {
        var refreshToken = refreshTokenService.createRefreshToken(email); // cria o token no banco de dados
        return generateCookie(refreshTokenCookieName, refreshToken.getToken(), "/api/authenticate/refreshtoken");
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        return getCookieValueByName(request, jwtCookieName);
    }

    public String getJwtRefreshFromCookies(HttpServletRequest request) {
        return getCookieValueByName(request, refreshTokenCookieName);
    }

    public ResponseCookie getCleanJwtCookie() {

        return ResponseCookie.from(jwtCookieName).path("/api").maxAge(0).build();
    }

    public ResponseCookie getCleanJwtRefreshCookie() {

        return ResponseCookie.from(refreshTokenCookieName).path("/api/authenticate/refreshtoken").maxAge(0)
        .build();
    }

    private ResponseCookie generateCookie(String name, String value, String path) { // path é caminho que o token é valido

        return ResponseCookie.from(name, value).path(path).maxAge((7 * 24 * 60 * 60)).httpOnly(true).build(); // cookie dura uma semana
    }

    private String getCookieValueByName(HttpServletRequest request, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

}
