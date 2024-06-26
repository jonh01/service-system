package com.servicesystem.api.domain.repositories;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicesystem.api.domain.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, UUID>  {

    boolean existsByUserIdAndServiceProvidedId(UUID userId, UUID serviceProvidedId);
}
