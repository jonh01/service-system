package com.servicesystem.api.domain.models.users;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.servicesystem.api.domain.models.enums.RegisteredUserType;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<RegisteredUserType> roles = new HashSet<>();

    private String name;

    public UserDetailsImpl(String email, String password, Collection<? extends GrantedAuthority> authorities, String name) {

        Set<RegisteredUserType> setRoles = authorities.stream()
                .map(authority -> fromAuthority(authority.getAuthority()))
                .collect(Collectors.toCollection(TreeSet::new));

        this.email = email;
        this.password = password;
        this.roles = setRoles;
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private RegisteredUserType fromAuthority(String authority) {

        switch (authority) {
            case "Provider":
                return RegisteredUserType.Provider;
            case "Admin":
                return RegisteredUserType.Admin;
            default:
                return RegisteredUserType.Client;
        }
    }

}
