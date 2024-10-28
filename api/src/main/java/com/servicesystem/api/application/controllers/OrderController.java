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

import com.servicesystem.api.application.payload.insert.OrderInsert;
import com.servicesystem.api.application.payload.response.OrderResponse;
import com.servicesystem.api.application.payload.update.OrderUpdate;
import com.servicesystem.api.application.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/allservices")
    ResponseEntity<Page<OrderResponse>> findAllByServiceUserId( 
            @RequestParam String userId,
            @ParameterObject @PageableDefault(size = 5, sort = "id") Pageable pageable ) {

        return ResponseEntity.ok(orderService.findAllByServiceUserId(userId, pageable));
    }
    
    @GetMapping
    ResponseEntity<Page<OrderResponse>> findAllByUser( 
            @RequestParam String userId, 
            @RequestParam(required = false) Boolean finished,
            @ParameterObject @PageableDefault(size = 5, sort = "id") Pageable pageable ) {

        if(finished != null)
            return ResponseEntity.ok(orderService.findAllByUserIdAndEndAt(userId, finished, pageable));
        else    
            return ResponseEntity.ok(orderService.findAllByUserId(userId,pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderResponse> findById (@PathVariable String id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid  @RequestBody OrderInsert orderInsert) {

        var newOrder = orderService.create(orderInsert);
        URI location = ServletUriComponentsBuilder.
        fromCurrentRequest().
        buildAndExpand(newOrder.getId())
        .toUri();
        
        return ResponseEntity.created(location).body(newOrder);
	}

    @PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody OrderUpdate orderUpdate) {

        orderService.update(id, orderUpdate);
        return ResponseEntity.noContent().build();
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
	
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByUserIdAndServiceProvidedIdAndEndAtIsNull(@RequestParam String userId, @RequestParam String serviceId) {
	
        var exists = orderService.existsByUserIdAndServiceProvidedIdAndEndAtIsNull(userId, serviceId);
        return ResponseEntity.ok(exists);
    }

}
