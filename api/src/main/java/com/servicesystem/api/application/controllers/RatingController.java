package com.servicesystem.api.application.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.servicesystem.api.application.payload.insert.RatingInsert;
import com.servicesystem.api.application.payload.response.RatingResponse;
import com.servicesystem.api.application.payload.update.RatingUpdate;
import com.servicesystem.api.application.services.RatingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping("/{id}")
    ResponseEntity<RatingResponse> findById (@PathVariable String id) {
        return ResponseEntity.ok(ratingService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RatingResponse> create(@Valid  @RequestBody RatingInsert ratingInsert) {

        var newRating = ratingService.create(ratingInsert);
        URI location = ServletUriComponentsBuilder.
        fromCurrentRequest().
        buildAndExpand(newRating.getId())
        .toUri();
        
        return ResponseEntity.created(location).body(newRating);
	}

    @PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody RatingUpdate ratingUpdate) {

        ratingService.update(id, ratingUpdate);
        return ResponseEntity.noContent().build();
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
	
        ratingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
