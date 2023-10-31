package com.servicesystem.api.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicesystem.api.domain.models.users.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {

}
