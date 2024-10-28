package com.servicesystem.api.application.controllers;

import java.net.URI;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.servicesystem.api.application.payload.insert.RatingInsert;
import com.servicesystem.api.application.payload.response.RatingResponse;
import com.servicesystem.api.application.payload.update.RatingUpdate;
import com.servicesystem.api.application.services.RatingService;
import com.servicesystem.api.domain.utils.ConverterUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping("/services/{serviceId}/ratings")
    ResponseEntity<Page<RatingResponse>> findAllByService( 
            @PathVariable String serviceId,
            @ParameterObject @PageableDefault(size = 5, sort = "id") Pageable pageable ) {

        return ResponseEntity.ok(ratingService.finAllByService(serviceId, pageable));
    }

    @GetMapping("/ratings/{id}")
    ResponseEntity<RatingResponse> findById (@PathVariable String id) {
        return ResponseEntity.ok(ratingService.findById(id));
    }
    @GetMapping("/ratings")
    ResponseEntity<RatingResponse> findByUserIdAndServiceProvidedId (@RequestParam String userId, @RequestParam String serviceId) {
        return ResponseEntity.ok(ratingService.findByUserIdAndServiceProvidedId(userId, serviceId));
    }

    @PostMapping("/ratings")
    public ResponseEntity<RatingResponse> create(@Valid  @RequestBody RatingInsert ratingInsert) {

        var newRating = ratingService.create(ratingInsert);
        URI location = ServletUriComponentsBuilder.
        fromCurrentRequest().
        buildAndExpand(newRating.getId())
        .toUri();
        
        return ResponseEntity.created(location).body(newRating);
	}

    @PutMapping("/ratings/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody RatingUpdate ratingUpdate) {

        ratingService.update(id, ratingUpdate);
        return ResponseEntity.noContent().build();
	}

    @DeleteMapping("/ratings/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
	
        ratingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ratings/exists")
    public ResponseEntity<Boolean> existsWithUserAndServiceProvide(@RequestParam String userId, @RequestParam String serviceId) {
	
        var exists = ratingService.existsWithUserAndServiceProvide(ConverterUtil.convertStringForUUID(userId), ConverterUtil.convertStringForUUID(serviceId));
        return ResponseEntity.ok(exists);
    }

}
