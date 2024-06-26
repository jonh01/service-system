package com.servicesystem.api.domain.repositories;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicesystem.api.domain.models.users.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
