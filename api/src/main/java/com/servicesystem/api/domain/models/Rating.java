package com.servicesystem.api.domain.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.servicesystem.api.domain.models.users.User;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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

    private Double note;

    @Column(columnDefinition="TEXT")
    private String comment;

    @ElementCollection
	@CollectionTable(name="tb_rating_image")
    private Set<String> images = new HashSet<>();

    @ManyToOne
	@JoinColumn(name="fk_user_id")
    private User user;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
	@JoinColumn(name="fk_service_provided_id")
    private ServiceProvided serviceProvided;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
