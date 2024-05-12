package com.servicesystem.api.domain.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    private Integer note;

    @Column(columnDefinition="TEXT")
    private String comment;

    @ElementCollection
	@CollectionTable(name="tb_rating_image")
    private Set<String> images = new HashSet<>();

    @ManyToOne
	@JoinColumn(name="user_id")
    private User user;

    @Column(name = "service_provided_id")
    private UUID serviceProvidedId;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
