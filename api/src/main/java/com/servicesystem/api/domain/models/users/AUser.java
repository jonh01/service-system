package com.servicesystem.api.domain.models.users;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.servicesystem.api.domain.models.enums.RegisteredUserType;

import lombok.Data;

@Data
public abstract class AUser {

    private UUID id;
	private String name;
	private String email;
	private String password;
    private String phone;
    private String image;
	private RegisteredUserType type;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
