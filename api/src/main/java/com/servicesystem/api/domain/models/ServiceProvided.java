package com.servicesystem.api.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.servicesystem.api.domain.models.enums.StatusService;
import com.servicesystem.api.domain.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_service")
public class ServiceProvided {

    @Id
    @GeneratedValue(generator = "UUID")
	private UUID id;

    private String name;
    private StatusService status;
    private String image;
    private String localAtuacao;
    private String description;

    @ManyToOne
	@JoinColumn(name="user_id")
    private User user;

    @ManyToOne
	@JoinColumn(name="service_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy="service", cascade = CascadeType.ALL)
	private List<Rating> ratings= new ArrayList<>();

    @Transient
    private Metrics metrics;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
