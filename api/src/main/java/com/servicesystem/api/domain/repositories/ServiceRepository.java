package com.servicesystem.api.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicesystem.api.domain.models.Service;

public interface ServiceRepository extends JpaRepository<Service, UUID> {

}
