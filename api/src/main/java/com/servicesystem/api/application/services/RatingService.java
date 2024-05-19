package com.servicesystem.api.application.services;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public RatingResponse findById (String id){
		
		Rating rating = ratingRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Avaliação não encontrada! Id "+ id ));

		return modelMapper.map(rating, RatingResponse.class);
	}

	@Transactional
	public RatingResponse create (RatingInsert ratingInsert){
             
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

    private boolean existsWithUserAndServiceProvide (UUID userId, UUID serviceProvidedId) {
        return ratingRepository.existsByUserIdAndServiceProvidedId(userId, serviceProvidedId);
    }

    private void updateRating (RatingUpdate ratingUp, Rating rating) {

        if (ratingUp.getComment() != null)
            rating.setComment(ratingUp.getComment());

        if(ratingUp.getNote() != null)
            rating.setNote(ratingUp.getNote());

        if(ratingUp.getImages().isEmpty()){
            for (String image : ratingUp.getImages()) {
                if(imageService.isBase64(image))
                    rating.getImages().add(imageService.saveNuvem(image));
                else
                    throw new BusinessException("Está imagem não corresponde ao padrão do sistema Base64!"); 
            }
        }
        
    }

}
