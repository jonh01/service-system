package com.servicesystem.api.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.servicesystem.api.domain.models.users.User;

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
@Entity(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
	private UUID id;

    private String description;
    private String local;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Double price;

    @ManyToOne
	@JoinColumn(name="fk_user_id")
    private User user;

    @ManyToOne
	@JoinColumn(name="fk_service_provided_id")
    private ServiceProvided serviceProvided;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
