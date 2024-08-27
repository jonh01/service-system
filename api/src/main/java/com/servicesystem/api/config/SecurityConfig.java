package com.servicesystem.api.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import com.servicesystem.api.application.services.UserDetailsServiceImpl;
import com.servicesystem.api.security.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    JWTAuthenticationFilter authenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider preAuthProvider = new PreAuthenticatedAuthenticationProvider();
        preAuthProvider
                .setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(userDetailsService));
        return preAuthProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, DaoAuthenticationProvider daoAuthProvider,
            PreAuthenticatedAuthenticationProvider preAuthProvider) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.authenticationProvider(daoAuthProvider)
                .authenticationProvider(preAuthProvider);
        return auth.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(withDefaults()).csrf(csrf -> csrf.disable())
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers( "/swagger-ui/**", "/docs/**").permitAll()
                        .requestMatchers("/authenticate/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()

                        .anyRequest().authenticated());

        http.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
