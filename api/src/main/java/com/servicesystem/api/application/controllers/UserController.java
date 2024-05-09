package com.servicesystem.api.application.controllers;

import java.net.URI;
import java.util.UUID;

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

import com.servicesystem.api.application.payload.insert.UserInsert;
import com.servicesystem.api.application.payload.response.UserResponse;
import com.servicesystem.api.application.payload.update.UserUpdate;
import com.servicesystem.api.application.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById (@PathVariable UUID id){

        return ResponseEntity.ok(userService.findById(id));
	}

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid  @RequestBody UserInsert userInsert) {

        var newUser = userService.create(userInsert);
        URI location = ServletUriComponentsBuilder.
        fromCurrentRequest().
        buildAndExpand(newUser.getId())
        .toUri();
        
        return ResponseEntity.created(location).body(newUser);
	}

    @PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable UUID id, @Valid @RequestBody UserUpdate userUpdate) {

        userService.update(id, userUpdate);
        return ResponseEntity.noContent().build();
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
	
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
