package com.servicesystem.api.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.servicesystem.api.domain.models.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
  boolean existsByUserEmail(String email);

  Optional<RefreshToken> findByToken(String token);

  Optional<RefreshToken> findByUserEmail(String email);

  @Modifying
  int deleteByUserEmail(String email);
}
