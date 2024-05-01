package com.servicesystem.api.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.servicesystem.api.domain.models.users.User;

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
@Entity(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
	private UUID id;

    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Double price;

    @ManyToOne
	@JoinColumn(name="user_id")
    private User user;

    @ManyToOne
	@JoinColumn(name="service_id")
    private Service service;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
