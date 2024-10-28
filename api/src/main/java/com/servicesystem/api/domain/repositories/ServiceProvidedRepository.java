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

       boolean existsByIdAndStatus(UUID id, StatusService status);

       boolean existsByIdAndUserId(UUID id, UUID userId);

                     @Query("SELECT s FROM tb_service_provided s " +
                     "WHERE (s.status = :status AND " +
                     "(LOWER(CAST(remove_accents(s.name) AS string)) ILIKE LOWER(CAST(remove_accents(CONCAT('%', :name, '%')) AS string)) " +
       "OR LOWER(CAST(remove_accents(s.description) AS string)) ILIKE LOWER(CAST(remove_accents(CONCAT('%', :description, '%')) AS string))))")
       Page<ServiceProvided> findAllByStatusAndNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                     @Param("status") StatusService status,
                     @Param("name") String name,
                     @Param("description") String description,
                     Pageable pageable);

       @Query("SELECT s FROM tb_service_provided s " +
                     "WHERE (s.category.id = :categoryId AND s.status = :status AND " +
                     "(LOWER(CAST(remove_accents(s.name) AS string)) ILIKE LOWER(CAST(remove_accents(CONCAT('%', :name, '%')) AS string)) " +
                     "OR LOWER(CAST(remove_accents(s.description) AS string)) ILIKE LOWER(CAST(remove_accents(CONCAT('%', :description, '%')) AS string))))")
       Page<ServiceProvided> findAllByCategoryIdAndStatusAndNameOrDescription(
                     @Param("categoryId") UUID categoryId,
                     @Param("status") StatusService status,
                     @Param("name") String name,
                     @Param("description") String description,
                     Pageable pageable);

       @Query("SELECT s FROM tb_service_provided s " +
                     "LEFT JOIN s.localAction la " +
                     "WHERE (s.status = :status AND " +
                     "(LOWER(CAST(remove_accents(s.name) AS string)) ILIKE LOWER(CAST(remove_accents(CONCAT('%', :name, '%')) AS string)) " +
                     "OR LOWER(CAST(remove_accents(s.description) AS string)) ILIKE LOWER(CAST(remove_accents(CONCAT('%', :description, '%')) AS string))) AND "+
                     "(LOWER(CAST(remove_accents(la) AS string)) ILIKE LOWER(CAST(remove_accents(CONCAT('%', :localAction, '%')) AS string))))")
       Page<ServiceProvided> findAllByStatusAndNameOrDescriptionAndLocalAction(
                     @Param("status") StatusService status,
                     @Param("name") String name,
                     @Param("description") String description,
                     @Param("localAction") String localAction,
                     Pageable pageable);

       @Query("SELECT s FROM tb_service_provided s " +
                     "LEFT JOIN s.localAction la " +
                     "WHERE (s.category.id = :categoryId AND s.status = :status AND " +
                     "(LOWER(CAST(remove_accents(s.name) AS string)) ILIKE LOWER(CAST(remove_accents(CONCAT('%', :name, '%')) AS string)) " +
                     "OR LOWER(CAST(remove_accents(s.description) AS string)) ILIKE LOWER(CAST(remove_accents(CONCAT('%', :description, '%')) AS string))) AND "+
                     "(LOWER(CAST(remove_accents(la) AS string)) ILIKE LOWER(CAST(remove_accents(CONCAT('%', :localAction, '%')) AS string))))")
       Page<ServiceProvided> findAllByCategoryIdAndStatusAndNameOrDescriptionAndLocalAction(
                     @Param("categoryId") UUID categoryId,
                     @Param("status") StatusService status,
                     @Param("name") String name,
                     @Param("description") String description,
                     @Param("localAction") String localAction,
                     Pageable pageable);

}
