package com.servicesystem.api.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.servicesystem.api.application.services.UserDetailsServiceImpl;
import com.servicesystem.api.domain.utils.CookieUtils;
import com.servicesystem.api.domain.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CookieUtils cookieUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain chain)
            throws ServletException, IOException, IllegalArgumentException {

        String token = parseJwt(request);

        if (token != null) {
            try {
                String email = jwtUtils.validateToken(token);

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException ex) {
                logger.error("Cannot set user authentication: {}", ex);
            }
        }
        chain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        return cookieUtils.getJwtFromCookies(request);
    }
}
