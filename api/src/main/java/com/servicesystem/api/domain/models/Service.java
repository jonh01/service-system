package com.servicesystem.api.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.servicesystem.api.domain.models.enums.StatusService;
import com.servicesystem.api.domain.models.users.Provider;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "tb_service")
public class Service {

    @Id
    @GeneratedValue(generator = "UUID")
	private UUID id;

    private String name;
    private StatusService status;
    private String localAtuacao;
    private String description;

    @ManyToOne
	@JoinColumn(name="client_id")
    private Provider provider;

    @ManyToOne
	@JoinColumn(name="service_id")
    private Category category;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
