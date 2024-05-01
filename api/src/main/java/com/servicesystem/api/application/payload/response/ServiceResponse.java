package com.servicesystem.api.application.payload.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.servicesystem.api.domain.models.enums.StatusService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {

    private UUID id;
    private String name;
    private StatusService status;
    private String localAtuacao;
    private String description;
    private UUID userId;
    private UUID categoryId;
    private LocalDateTime createdAt;

}
