package com.servicesystem.api.application.payload.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

	private UUID id;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Double price;
    private UUID userId;
    private UUID serviceId;
    private LocalDateTime createdAt;
}
