package com.servicesystem.api.application.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.servicesystem.api.application.payload.insert.ServiceProvidedInsert;
import com.servicesystem.api.application.payload.response.ServiceProvidedResponse;
import com.servicesystem.api.application.payload.response.ServiceProvidedSummaryResponse;
import com.servicesystem.api.application.payload.response.ServiceProvidedUserResponse;
import com.servicesystem.api.application.payload.update.ServiceProvidedUpdate;
import com.servicesystem.api.domain.exceptions.BusinessException;
import com.servicesystem.api.domain.exceptions.ObjectNotFoundException;
import com.servicesystem.api.domain.models.ServiceProvided;
import com.servicesystem.api.domain.models.enums.RegisteredUserType;
import com.servicesystem.api.domain.models.enums.StatusService;
import com.servicesystem.api.domain.repositories.ServiceProvidedRepository;
import com.servicesystem.api.domain.utils.ConverterUtil;

import jakarta.transaction.Transactional;

@Service
public class ServiceProvidedService {

    @Autowired
    private ServiceProvidedRepository serviceProvidedRepository;

    @Autowired
	private ModelMapper modelMapper;
    
    @Autowired
	private ImageService imageService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    public Page<ServiceProvidedUserResponse> findAllByUserId (String userId, Pageable pageable){

        Page<ServiceProvided> page = serviceProvidedRepository.findAllByUserId(ConverterUtil.convertStringForUUID(userId), pageable);
        return page.map(serviceProvided -> modelMapper.map(serviceProvided, ServiceProvidedUserResponse.class));
    }

    public Page<ServiceProvidedSummaryResponse> findAllByName (String name, StatusService status, Pageable pageable){

        String formatedName = ConverterUtil.removeAccents(name);
        System.out.println(formatedName);
        Page<ServiceProvided> page = serviceProvidedRepository.findAllByStatusAndNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(status, formatedName, formatedName, pageable);
        return page.map(serviceProvided -> modelMapper.map(serviceProvided, ServiceProvidedSummaryResponse.class));
    }

    public Page<ServiceProvidedSummaryResponse> findAllByNameAndCategoryIdAndStatus (String name, String categoryId, StatusService status, Pageable pageable){

        String formatedName = ConverterUtil.removeAccents(name);
        
        Page<ServiceProvided> page = serviceProvidedRepository.findAllByCategoryIdAndStatusAndNameOrDescription(ConverterUtil.convertStringForUUID(categoryId), status, formatedName, formatedName, pageable);
        return page.map(serviceProvided -> modelMapper.map(serviceProvided, ServiceProvidedSummaryResponse.class));
    }

    public Page<ServiceProvidedSummaryResponse> findAllByStatusAndNameOrDescriptionAndLocalAction (String name, StatusService status, String local, Pageable pageable){

        String formatedName = ConverterUtil.removeAccents(name);

        Page<ServiceProvided> page = serviceProvidedRepository.findAllByStatusAndNameOrDescriptionAndLocalAction(status, formatedName, formatedName, local, pageable);
        return page.map(serviceProvided -> modelMapper.map(serviceProvided, ServiceProvidedSummaryResponse.class));
    }

    public Page<ServiceProvidedSummaryResponse> findAllByNameAndCategoryIdAndStatusAndLocalAction (String name, String categoryId, StatusService status, String local, Pageable pageable){

        String formatedName = ConverterUtil.removeAccents(name);

        Page<ServiceProvided> page = serviceProvidedRepository.findAllByCategoryIdAndStatusAndNameOrDescriptionAndLocalAction(ConverterUtil.convertStringForUUID(categoryId), status, formatedName, formatedName, local, pageable);
        return page.map(serviceProvided -> modelMapper.map(serviceProvided, ServiceProvidedSummaryResponse.class));
    }

    public ServiceProvidedResponse findById (String id){
		
		ServiceProvided serviceProvided = serviceProvidedRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Serviço não encontrado! Id "+ id ));

		return modelMapper.map(serviceProvided, ServiceProvidedResponse.class);
	}

	@Transactional
	public ServiceProvidedResponse create (ServiceProvidedInsert serviceProvidedInsert){

        String email = AuthService.userLogged();

        if(serviceProvidedInsert.getCategory().getId() != null && !categoryService.existsById(serviceProvidedInsert.getCategory().getId().toString())){
            throw new BusinessException("Não é possível criar um novo serviço. Esta categoria Não existe.");
        }

        if(serviceProvidedInsert.getUser().getId() != null && !userService.existsById(serviceProvidedInsert.getUser().getId().toString())){
            throw new BusinessException("Não é possível criar um novo serviço. Este usuário não existe.");
        }

        if(!userService.existsByEmailAndType(email, RegisteredUserType.Provider)){
            userService.updateType(email, RegisteredUserType.Provider);
        }

        var service = modelMapper.map(serviceProvidedInsert, ServiceProvided.class);
        service.setStatus(StatusService.Active);
        service.setNumReviews(0);
        service.setSumReviews(0);

        if(serviceProvidedInsert.getImage() != null){

            if(imageService.isBase64(serviceProvidedInsert.getImage())){
                String image64 = serviceProvidedInsert.getImage();
                service.setImage(imageService.saveNuvem(image64));
            }
                else
                    throw new BusinessException("Está imagem não corresponde ao padrão do sistema Base64!"); 
        }

		return modelMapper.map(
			serviceProvidedRepository.save(service), ServiceProvidedResponse.class
		);
	}

    @Transactional
	public ServiceProvidedResponse update (String id, ServiceProvidedUpdate serviceProvidedUpdate) {

		ServiceProvided searchedServiceProvided = serviceProvidedRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Serviço não encontrado! Id "+ id ));

        updateServiceProvided(serviceProvidedUpdate, searchedServiceProvided);
		
		return modelMapper.map(serviceProvidedRepository.save(searchedServiceProvided), ServiceProvidedResponse.class);
	}

    public void delete (String id) {
		ServiceProvidedResponse searchedServiceProvided = findById(id);
		serviceProvidedRepository.deleteById(searchedServiceProvided.getId());
	}

    @Transactional
	public ServiceProvidedResponse attStatus (String id, StatusService status){

        ServiceProvided searchedServiceProvided = serviceProvidedRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Serviço não encontrado! Erro ao atualizar status Id "+ id ));

        searchedServiceProvided.setStatus(status);

		return modelMapper.map(
			serviceProvidedRepository.save(searchedServiceProvided), ServiceProvidedResponse.class
		);
	}

    public boolean existsById(String id){

        return serviceProvidedRepository.existsById(ConverterUtil.convertStringForUUID(id));
    }

    public boolean existsByIdAndStatus(String id, StatusService status){

        return serviceProvidedRepository.existsByIdAndStatus(ConverterUtil.convertStringForUUID(id), status);
    }

    public boolean existsByIdAndUserId(String id, String userId){
        return serviceProvidedRepository.existsByIdAndUserId(ConverterUtil.convertStringForUUID(id), ConverterUtil.convertStringForUUID(userId));
    }

    private void updateServiceProvided (ServiceProvidedUpdate serviceProvidedUp, ServiceProvided serviceProvided) {

        if (serviceProvidedUp.getImage() != null){

            if(imageService.isBase64(serviceProvidedUp.getImage()))
                serviceProvided.setImage(imageService.saveNuvem(serviceProvidedUp.getImage()));
            else
                throw new BusinessException("Está imagem não corresponde ao padrão do sistema Base64!"); 
        }
        if(serviceProvidedUp.getDescription() != null)
            serviceProvided.setDescription(serviceProvidedUp.getDescription());

        if(serviceProvidedUp.getLocalAction() != null){
            serviceProvided.setLocalAction(serviceProvidedUp.getLocalAction());
        }
        
    }

}
