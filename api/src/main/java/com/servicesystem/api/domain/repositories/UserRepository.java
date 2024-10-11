package com.servicesystem.api.domain.repositories;
import java.util.UUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.servicesystem.api.domain.models.enums.RegisteredUserType;
import com.servicesystem.api.domain.models.users.User;


public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
        "FROM tb_user u " +
        "LEFT JOIN u.type ut " +
        "WHERE (u.email = :email AND " +
        "ut = :type)")
    boolean existsByEmailAndType(@Param("email") String email, @Param("type") RegisteredUserType type);

    Optional<User> findByEmail(String email);
}
