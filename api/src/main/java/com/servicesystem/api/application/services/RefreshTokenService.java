package com.servicesystem.api.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.servicesystem.api.domain.models.RefreshToken;
import com.servicesystem.api.domain.models.enums.TokenType;
import com.servicesystem.api.domain.repositories.RefreshTokenRepository;
import com.servicesystem.api.domain.utils.JwtUtils;

import jakarta.transaction.Transactional;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JwtUtils jwtUtils;

    public RefreshToken findByToken(String token) {

        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("Token não encontrado! Token: " + token));


    }

    public RefreshToken findByEmail(String email) {
        return refreshTokenRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Token não encontrado! Email: " + email));

    }

    public RefreshToken createRefreshToken(String email) {

        var refreshToken = new RefreshToken();
        if (existsByEmail(email)){
            refreshToken = findByEmail(email);
        }
        else {
        refreshToken.setUserEmail(email);
        }

        String jwt = jwtUtils.generateToken(userDetailsServiceImpl.loadUserByUsername(email), TokenType.RefreshToken);

        refreshToken.setToken(jwt);

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Transactional
    public int deleteByEmail(String email) {
        return refreshTokenRepository.deleteByUserEmail(email);
    }

    public boolean existsByEmail(String email) {
        return refreshTokenRepository.existsByUserEmail(email);
    }
}
