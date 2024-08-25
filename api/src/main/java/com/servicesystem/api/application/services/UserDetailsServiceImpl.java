package com.servicesystem.api.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.servicesystem.api.domain.exceptions.ObjectNotFoundException;
import com.servicesystem.api.domain.models.users.User;
import com.servicesystem.api.domain.models.users.UserDetailsImpl;
import com.servicesystem.api.domain.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetailsImpl loadUserByUsername(String email){
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Email: " + email));

		return new UserDetailsImpl(user.getEmail(),"",user.getType(),user.getName());
	}
}
