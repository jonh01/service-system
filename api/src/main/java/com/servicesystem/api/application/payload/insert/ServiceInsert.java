package com.servicesystem.api.application.payload.insert;

import java.util.UUID;

import com.servicesystem.api.domain.models.enums.StatusService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInsert {

    @NotBlank
    private String name;

    private StatusService status;

    @NotBlank
    private String localAtuacao;

    @NotBlank
    private String description;

    @NotBlank
    private UUID userId;

    @NotBlank
    private UUID categoryId;

}
