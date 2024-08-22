package com.servicesystem.api.application.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.servicesystem.api.application.payload.response.UserResponse;
import com.servicesystem.api.domain.exceptions.ObjectNotFoundException;
import com.servicesystem.api.domain.models.users.User;

@Service
public class AuthService {

    public void userPermission() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
    }
    
    // 	public UserResponse findByEmail (String email){
		
	// 	User user = userRepository.findByEmail(email)
    //     .orElseThrow(() -> new ObjectNotFoundException(
    //         "Usuário não encontrado! Email: "+ email ));

	// 	return modelMapper.map(user, UserResponse.class);
	// }
}
