package com.servicesystem.api.domain.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.servicesystem.api.domain.models.ServiceProvided;
import com.servicesystem.api.domain.models.enums.StatusService;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, UUID> {

    Page<ServiceProvided> findAllByUserId(UUID id, Pageable pageable);

    Page<ServiceProvided> findAllByCategoryIdAndStatus(UUID categoryId, StatusService status, Pageable pageable);
}
