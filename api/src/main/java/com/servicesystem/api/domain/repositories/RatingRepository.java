package com.servicesystem.api.domain.repositories;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.servicesystem.api.domain.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, UUID>  {

    Page<Rating> findAllByServiceProvidedId(UUID id, Pageable pageable);
}
