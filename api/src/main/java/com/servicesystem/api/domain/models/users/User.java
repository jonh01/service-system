package com.servicesystem.api.domain.models.users;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

import org.hibernate.annotations.CreationTimestamp;
import com.servicesystem.api.domain.models.enums.RegisteredUserType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tb_users")
public class User {

    private UUID id;
	private String name;
	private String email;
	private String password;
    private String phone;
    private String image;

    @ElementCollection
    @CollectionTable(name = "tb_type_user")
    private Set<RegisteredUserType> type = new HashSet<>(); 

    @CreationTimestamp
    private LocalDateTime createdAt;

}
