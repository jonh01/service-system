package com.servicesystem.api.domain.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.servicesystem.api.domain.models.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Page<Order> findAllByUserIdAndEndAtIsNull(UUID id, Pageable pageable);
    Page<Order> findAllByUserIdAndEndAtIsNotNull(UUID id, Pageable pageable);

    Page<Order> findAllByServiceProvidedUser_Id(UUID userId, Pageable pageable);

    boolean existsByIdAndEndAtIsNull(UUID id);
}
