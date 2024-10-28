package com.servicesystem.api.application.services;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicesystem.api.application.payload.insert.UserInsert;
import com.servicesystem.api.application.payload.response.UserResponse;
import com.servicesystem.api.application.payload.update.UserUpdate;
import com.servicesystem.api.domain.exceptions.BusinessException;
import com.servicesystem.api.domain.exceptions.ObjectNotFoundException;
import com.servicesystem.api.domain.models.enums.RegisteredUserType;
import com.servicesystem.api.domain.models.users.User;
import com.servicesystem.api.domain.repositories.UserRepository;
import com.servicesystem.api.domain.utils.ConverterUtil;

import jakarta.transaction.Transactional;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ImageService imageService;


    public UserResponse findById (String id){
		
		User user = userRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Usuário não encontrado! Id "+ id ));

		return modelMapper.map(user, UserResponse.class);
	}

	public UserResponse findByEmail (String email){
		
		User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new ObjectNotFoundException(
            "Usuário não encontrado! Email: "+ email ));

		return modelMapper.map(user, UserResponse.class);
	}

	@Transactional
	public UserResponse create (UserInsert userInsert){

		if(!userInsert.getEmail().isBlank() && existsByEmail(userInsert.getEmail()))
			throw new BusinessException("O email fornecido é inválido ou já está em uso.");

		if(!userInsert.getCpf().isBlank() && existsByCpf(userInsert.getCpf()))
			throw new BusinessException("O CPF fornecido é inválido ou já está em uso.");

		Set<RegisteredUserType> type = new HashSet<>(); 
		type.add(RegisteredUserType.Client);
		userInsert.setType(type);

		if(userInsert.getImage() != null){

            if(imageService.isBase64(userInsert.getImage())){
                String image64 = userInsert.getImage();
                userInsert.setImage(imageService.saveNuvem(image64));
            }
            else if(!containsPattern(userInsert.getImage()))
                throw new BusinessException("Está imagem não corresponde ao padrão do sistema Base64 ou é uma imagem de perfil do Google!"); 

		}

		return modelMapper.map(
			userRepository.save(modelMapper.map(userInsert, User.class)), 
			UserResponse.class
		);
	}

    @Transactional
	public UserResponse update (String id, UserUpdate userUpdate) {

		if(userUpdate.getEmail() != null && existsByEmail(userUpdate.getEmail()))
			throw new BusinessException("O email fornecido é inválido ou já está em uso.");

		User searchedUser = userRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Usuário não encontrado! Id "+ id ));

			updateUser(userUpdate, searchedUser);
		
		return modelMapper.map(userRepository.save(searchedUser), UserResponse.class);
	}

	@Transactional
	public UserResponse updateType (String email, RegisteredUserType type) {

		if(email != null && !existsByEmail(email))
			throw new BusinessException("O email fornecido é inválido.");

		User searchedUser = userRepository.findByEmail(email)
        .orElseThrow(() -> new ObjectNotFoundException(
            "Usuário não encontrado! Email "+ email ));

		searchedUser.getType().add(type);
		
		return modelMapper.map(userRepository.save(searchedUser), UserResponse.class);
	}

    public void delete (String id) {
		UserResponse searchedUser = findById(id);
		userRepository.deleteById(searchedUser.getId());
	}

	public boolean existsById(String id) {
        return userRepository.existsById(ConverterUtil.convertStringForUUID(id));
    }

	public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

	public boolean existsByCpf(String email) {
        return userRepository.existsByCpf(email);
    }

	public boolean existsByEmailAndType(String email, RegisteredUserType type){
		return userRepository.existsByEmailAndType(email, type);
	}

	private void updateUser(UserUpdate usuUp, User user){

		if(usuUp.getEmail() != null)
			user.setEmail(usuUp.getEmail());
		
		if(usuUp.getImage() != null){
			if(imageService.isBase64(usuUp.getImage()))
				user.setImage(imageService.saveNuvem(usuUp.getImage()));
		else
			throw new BusinessException("Está imagem não corresponde ao padrão do sistema Base64!"); 
		}

		if(usuUp.getName() != null)
			user.setName(usuUp.getName());
			
		if(usuUp.getPhone() != null)
			user.setPhone(usuUp.getPhone());

		if(usuUp.getType().isEmpty())
			user.setType(usuUp.getType());

	}

	public static boolean containsPattern(String text) {

        // Define a regex para corresponder ao padrão: https<qualquer coisa>google<qualquer coisa>
        String regex = "https.*google.*";
        
        // Cria o padrão e o matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        // Retorna se a string contém o padrão
        return matcher.find();
    }
    
}
