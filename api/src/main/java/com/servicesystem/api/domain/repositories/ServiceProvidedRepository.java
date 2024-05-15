package com.servicesystem.api.domain.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.servicesystem.api.domain.models.ServiceProvided;
import com.servicesystem.api.domain.models.enums.StatusService;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, UUID> {

    Page<ServiceProvided> findAllByUserId(UUID id, Pageable pageable);

    Page<ServiceProvided> findAllByCategoryIdAndStatus(UUID categoryId, StatusService status, Pageable pageable);

    Page<ServiceProvided> findAllByStatusAndNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(StatusService status, String name, String description, Pageable pageable);
    @Query("SELECT s FROM tb_service_provided s " +
           "WHERE (s.category.id = :categoryId AND s.status = :status AND " +
           "(LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(s.description) LIKE LOWER(CONCAT('%', :description, '%'))))")
    Page<ServiceProvided> findAllByCategoryIdAndStatusAndNameOrDescription(
            @Param("categoryId") UUID categoryId, 
            @Param("status") StatusService status, 
            @Param("name") String name, 
            @Param("description") String description, 
            Pageable pageable);

    
}
