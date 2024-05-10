package com.servicesystem.api.application.payload.insert;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInsert {

    @Schema(description = "Descrição da ordem", example = "Troca de tela de notebook")
    @NotBlank
    private String description;

    @Schema(description = "Data do começo do serviço", example = "2024-05-09T24:00:00")
    private LocalDateTime startAt;

    @Schema(description = "Preço base do serviço", example = "29.99")
    @NotBlank
    private Double price;

    @NotBlank
    private UUID userId;

    @NotBlank
    private UUID serviceProvidedId;
}
