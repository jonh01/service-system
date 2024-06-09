package com.servicesystem.api.application.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicesystem.api.application.payload.insert.UserInsert;
import com.servicesystem.api.application.payload.response.UserResponse;
import com.servicesystem.api.application.payload.update.UserUpdate;
import com.servicesystem.api.domain.exceptions.BusinessException;
import com.servicesystem.api.domain.exceptions.ObjectNotFoundException;
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

		if(userInsert.getImage() != null){

			// userInsert.setImage("https:\\\\/\\\\/iili.io\\\\/Jpi4LwF.jpg"); // utilizar para testes de autenticação

            if(imageService.isBase64(userInsert.getImage())){
                String image64 = userInsert.getImage();
                userInsert.setImage(imageService.saveNuvem(image64));
            }
            else
                throw new BusinessException("Está imagem não corresponde ao padrão do sistema Base64!"); 

		}

		return modelMapper.map(
			userRepository.save(modelMapper.map(userInsert, User.class)), 
			UserResponse.class
		);
	}

    @Transactional
	public UserResponse update (String id, UserUpdate userUpdate) {

		if(!userUpdate.getEmail().isBlank() && existsByEmail(userUpdate.getEmail()))
			throw new BusinessException("O email fornecido é inválido ou já está em uso.");

		User searchedUser = userRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Usuário não encontrado! Id "+ id ));

			updateUser(userUpdate, searchedUser);
		
		return modelMapper.map(userRepository.save(searchedUser), UserResponse.class);
	}

    public void delete (String id) {
		UserResponse searchedUser = findById(id);
		userRepository.deleteById(searchedUser.getId());
	}

	public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

	public boolean existsByCpf(String email) {
        return userRepository.existsByCpf(email);
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
    
}
