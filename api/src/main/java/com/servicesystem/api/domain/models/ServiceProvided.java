package com.servicesystem.api.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.servicesystem.api.domain.models.enums.StatusService;
import com.servicesystem.api.domain.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_service_provided")
public class ServiceProvided {

    @Id
    @GeneratedValue(generator = "UUID")
	private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private StatusService status;

    private String image;

    @Column(columnDefinition="TEXT")
    private String description;

    @ElementCollection
	@CollectionTable(name="tb_service_provided_localAction")
    private Set<String> localAction = new HashSet<>();

    @ManyToOne
	@JoinColumn(name="fk_user_id")
    private User user;

    @ManyToOne
	@JoinColumn(name="fk_category_id")
    private Category category;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer numReviews;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer sumReviews;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_service_provided_id")
    private Set<ReviewsNote> numReviewsNote = new HashSet<>();

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
