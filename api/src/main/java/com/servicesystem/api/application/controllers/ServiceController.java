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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.servicesystem.api.application.payload.insert.ServiceProvidedInsert;
import com.servicesystem.api.application.payload.response.ServiceProvidedResponse;
import com.servicesystem.api.application.payload.response.ServiceProvidedSummaryResponse;
import com.servicesystem.api.application.payload.response.ServiceProvidedUserResponse;
import com.servicesystem.api.application.payload.update.ServiceProvidedUpdate;
import com.servicesystem.api.application.services.ServiceProvidedService;
import com.servicesystem.api.domain.models.enums.StatusService;

import jakarta.validation.Valid;
@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class ServiceController {

    @Autowired
    ServiceProvidedService serviceProvidedService;
    
    @GetMapping("/users/{userId}/services")
    ResponseEntity<Page<ServiceProvidedUserResponse>> findAllByUser( 
            @PathVariable String userId,
            @ParameterObject @PageableDefault(size = 5, sort = "id") Pageable pageable ) {

        return ResponseEntity.ok(serviceProvidedService.findAllByUserId(userId, pageable));
    }

    @GetMapping("/services")
    ResponseEntity<Page<ServiceProvidedSummaryResponse>> findAllByStatus( 
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String local,
            @RequestParam String name,
            @RequestParam StatusService status,
            @ParameterObject @PageableDefault(size = 5, sort = "id") Pageable pageable ) {

        if(name != null && categoryId != null && local != null)
            return ResponseEntity.ok(serviceProvidedService.findAllByNameAndCategoryIdAndStatusAndLocalAction(name, categoryId, status, local, pageable));
        else if(name != null && categoryId != null)
            return ResponseEntity.ok(serviceProvidedService.findAllByNameAndCategoryIdAndStatus(name, categoryId, status, pageable));

        if(categoryId == null && name != null && local != null)
            return ResponseEntity.ok(serviceProvidedService.findAllByStatusAndNameOrDescriptionAndLocalAction(name, status, local, pageable));
        else
            return ResponseEntity.ok(serviceProvidedService.findAllByName(name, status, pageable));
    }

    @GetMapping("/services/{id}")
    ResponseEntity<ServiceProvidedResponse> findById (@PathVariable String id) {
        return ResponseEntity.ok(serviceProvidedService.findById(id));
    }

    @PostMapping("/services")
    public ResponseEntity<ServiceProvidedResponse> create(@Valid  @RequestBody ServiceProvidedInsert serviceProvidedInsert) {

        var newServiceProvided = serviceProvidedService.create(serviceProvidedInsert);
        URI location = ServletUriComponentsBuilder.
        fromCurrentRequest().
        buildAndExpand(newServiceProvided.getId())
        .toUri();
        
        return ResponseEntity.created(location).body(newServiceProvided);
	}

    @PutMapping("/services/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody ServiceProvidedUpdate serviceProvidedUpdate) {

        serviceProvidedService.update(id, serviceProvidedUpdate);
        return ResponseEntity.noContent().build();
	}

    @DeleteMapping("/services/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
	
        serviceProvidedService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/services/{id}")
	public ResponseEntity<Void> statusUpdate(@PathVariable String id, @RequestParam StatusService status) {

        serviceProvidedService.attStatus(id, status);
        return ResponseEntity.noContent().build();
	}

}
