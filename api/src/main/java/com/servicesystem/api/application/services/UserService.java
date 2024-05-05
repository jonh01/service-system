package com.servicesystem.api.application.services;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicesystem.api.application.payload.response.UserResponse;
import com.servicesystem.api.application.payload.update.UserUpdate;
import com.servicesystem.api.domain.exceptions.ObjectNotFoundException;
import com.servicesystem.api.domain.models.users.User;
import com.servicesystem.api.domain.repositories.UserRepository;
import com.servicesystem.api.utils.BeanUtilsHelper;

import jakarta.transaction.Transactional;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
	private ModelMapper modelMapper;


    public UserResponse findById (UUID id){
		
		User user = userRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException(
            "Usuário não encontrada! Id "+ id ));

		return modelMapper.map(user, UserResponse.class);
	}

    @Transactional
	public UserResponse update(UUID id, UserUpdate userUpdate) {
		User searchedUser = userRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException(
            "Usuário não encontrada! Id "+ id ));

		BeanUtilsHelper.copyNonNullProperties(searchedUser, userUpdate);
		return modelMapper.map(userRepository.save(searchedUser), UserResponse.class);
	}

    public void delete(UUID id) {
		UserResponse searchedUser = findById(id);
		userRepository.deleteById(searchedUser.getId());
	}
    
}
