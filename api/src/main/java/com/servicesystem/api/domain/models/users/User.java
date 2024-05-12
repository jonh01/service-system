package com.servicesystem.api.domain.models.users;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

import com.servicesystem.api.domain.models.enums.RegisteredUserType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
	private String name;

    @Column(unique=true)
	private String email;

	private String cpf;
    private String phone;
    private String image;

    @ElementCollection
    @CollectionTable(name = "tb_type_user")
    private Set<RegisteredUserType> type = new HashSet<>(); 

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
