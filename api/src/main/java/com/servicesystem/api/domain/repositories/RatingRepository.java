package com.servicesystem.api.domain.repositories;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.servicesystem.api.domain.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, UUID>  {

    boolean existsByUserIdAndServiceProvidedId(UUID userId, UUID serviceProvidedId);

    Page<Rating> findAllByServiceProvidedId(UUID serviceId, Pageable pageable);

    Optional<Rating> findByUserIdAndServiceProvidedId(UUID userId, UUID serviceProvidedId);

}
