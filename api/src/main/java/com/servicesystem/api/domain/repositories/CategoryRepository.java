package com.servicesystem.api.domain.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.servicesystem.api.domain.models.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @NonNull
    Page<Category> findAll(@NonNull Pageable pageable);

    boolean existsByName(String name);

}
