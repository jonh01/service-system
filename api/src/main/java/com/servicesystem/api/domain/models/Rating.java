package com.servicesystem.api.domain.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.servicesystem.api.domain.models.users.Client;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
@Entity(name = "tb_rating")
public class Rating {

    @Id
    @GeneratedValue(generator = "UUID")
	private UUID id;

    private String note;
    private String comment;

    @ElementCollection
	@CollectionTable(name="tb_rating_image")
    private Set<String> images = new HashSet<>();

    @ManyToOne
	@JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
	@JoinColumn(name="service_id")
    private Service service;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
