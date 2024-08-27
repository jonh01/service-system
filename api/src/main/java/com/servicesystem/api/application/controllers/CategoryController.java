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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.servicesystem.api.application.payload.insert.CategoryInsert;
import com.servicesystem.api.application.payload.response.CategoryResponse;
import com.servicesystem.api.application.payload.update.CategoryUpdate;
import com.servicesystem.api.application.services.AuthService;
import com.servicesystem.api.application.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    AuthService authService;
    
    @GetMapping
    ResponseEntity<Page<CategoryResponse>> findAll(
            @ParameterObject @PageableDefault(size = 5, sort = "id") Pageable pageable ) {

        authService.userPermission();
        
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryResponse> findById (@PathVariable String id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid  @RequestBody CategoryInsert categoryInsert) {

        var newCategory = categoryService.create(categoryInsert);
        URI location = ServletUriComponentsBuilder.
        fromCurrentRequest().
        buildAndExpand(newCategory.getId())
        .toUri();
        
        return ResponseEntity.created(location).body(newCategory);
	}

    @PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody CategoryUpdate categoryUpdate) {

        categoryService.update(id, categoryUpdate);
        return ResponseEntity.noContent().build();
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
	
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
