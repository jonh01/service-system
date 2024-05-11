package com.servicesystem.api.domain.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.servicesystem.api.domain.models.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Page<Category> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    boolean existsByName(String name);

}
