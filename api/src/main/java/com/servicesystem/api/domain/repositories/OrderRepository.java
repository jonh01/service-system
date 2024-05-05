package com.servicesystem.api.domain.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.servicesystem.api.domain.models.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Page<Order> findAllByUserId(UUID id, Pageable pageable);
    Page<Order> findAllByUserIdAndEndAtIsNull(UUID id, Pageable pageable);
    Page<Order> findAllByUserIdAndEndAtIsNotNull(UUID id, Pageable pageable);
}
