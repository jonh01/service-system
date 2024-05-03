package com.servicesystem.api.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicesystem.api.domain.models.ServiceProvided;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, UUID> {

}
