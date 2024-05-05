package com.servicesystem.api.domain.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.servicesystem.api.domain.models.users.User;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_rating")
public class Rating {

    @Id
    @GeneratedValue(generator = "UUID")
	private UUID id;

    private Short note;
    private String comment;

    @ElementCollection
	@CollectionTable(name="tb_rating_image")
    private Set<String> images = new HashSet<>();

    @ManyToOne
	@JoinColumn(name="user_id")
    private User user;

    @ManyToOne
	@JoinColumn(name="service_provided_id")
    private ServiceProvided serviceProvided;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
