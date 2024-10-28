package com.servicesystem.api.application.services;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.servicesystem.api.application.payload.insert.RatingInsert;
import com.servicesystem.api.application.payload.response.RatingResponse;
import com.servicesystem.api.application.payload.update.RatingUpdate;
import com.servicesystem.api.domain.exceptions.BusinessException;
import com.servicesystem.api.domain.exceptions.ObjectNotFoundException;
import com.servicesystem.api.domain.models.Rating;
import com.servicesystem.api.domain.repositories.RatingRepository;
import com.servicesystem.api.domain.utils.ConverterUtil;

import jakarta.transaction.Transactional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
	private ModelMapper modelMapper;

    @Autowired
	private ImageService imageService;

    @Autowired
    private ServiceProvidedService providedService;

    @Autowired
    private UserService userService;

    public Page<RatingResponse> finAllByService (String seviceId, Pageable pageable ) {
        
      Page<Rating> page = ratingRepository.findAllByServiceProvidedId(ConverterUtil.convertStringForUUID(seviceId), pageable);
        return page.map(rating -> modelMapper.map(rating, RatingResponse.class));
    }

    public RatingResponse findById (String id){
		
		Rating rating = ratingRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Avaliação não encontrada! Id "+ id ));

		return modelMapper.map(rating, RatingResponse.class);
	}
    
    public RatingResponse findByUserIdAndServiceProvidedId (String userId, String serviceProvidedId) {

        Rating rating = ratingRepository.findByUserIdAndServiceProvidedId(ConverterUtil.convertStringForUUID(userId), ConverterUtil.convertStringForUUID(serviceProvidedId))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Avaliação não encontrada!"));

		return modelMapper.map(rating, RatingResponse.class);
    }

	@Transactional
	public RatingResponse create (RatingInsert ratingInsert){

        if(ratingInsert.getUser().getId() != null && !userService.existsById(ratingInsert.getUser().getId().toString())){
            throw new BusinessException("Não é possível criar uma nova avaliação. Este usuário não existe.");
        }

        if(ratingInsert.getServiceProvided().getId() != null && !providedService.existsById(ratingInsert.getServiceProvided().getId().toString())){
            throw new BusinessException("Não é possível criar uma nova avaliação pois o serviço não existe.");
        }
             
        if (existsWithUserAndServiceProvide(ratingInsert.getUser().getId(), ratingInsert.getServiceProvided().getId()))
            throw new BusinessException("Avaliação já cadastrada!"); 

        if(ratingInsert.getImages() != null && !ratingInsert.getImages().isEmpty()){

            Set<String> images = new HashSet<>();
            for (String image : ratingInsert.getImages()) {
                if(imageService.isBase64(image))
                    images.add(imageService.saveNuvem(image));
                else
                    throw new BusinessException("Está imagem não corresponde ao padrão do sistema Base64!"); 
            }
            ratingInsert.setImages(images);
        }

		return modelMapper.map(
			ratingRepository.save(modelMapper.map(ratingInsert, Rating.class)), RatingResponse.class
		);
	}

    @Transactional
	public RatingResponse update (String id, RatingUpdate ratingUpdate) {

		Rating searchedRating = ratingRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Avaliação não encontrada! Id "+ id ));

		updateRating(ratingUpdate, searchedRating);
		
		return modelMapper.map(ratingRepository.save(searchedRating), RatingResponse.class);
	}

    public void delete (String id) {
		RatingResponse searchedRating = findById(id);
		ratingRepository.deleteById(searchedRating.getId());
	}

    public boolean existsWithUserAndServiceProvide (UUID userId, UUID serviceProvidedId) {
        return ratingRepository.existsByUserIdAndServiceProvidedId(userId, serviceProvidedId);
    }

    private void updateRating (RatingUpdate ratingUp, Rating rating) {

        if (ratingUp.getComment() != null)
            rating.setComment(ratingUp.getComment());

        if(ratingUp.getNote() != null)
            rating.setNote(ratingUp.getNote());

        if(ratingUp.getImages().isEmpty()){
            var images = rating.getImages();
            int error = 0;
            for (String image : ratingUp.getImages()) {
                if(imageService.isBase64(image))
                    images.add(imageService.saveNuvem(image));
                else
                    error++;  
            }
            rating.setImages(images);

            if(error > 0)
                throw new BusinessException("Algumas imagens não seguem o padrão correto. Verifique as imagens que foram atualizadas."); 
        }
        
    }

}
