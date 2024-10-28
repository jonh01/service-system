package com.servicesystem.api.application.payload.update;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdate {

    @Schema(description = "Data do começo do serviço", example = "2024-05-09T24:00:00")
    private LocalDateTime startAt;

    @Schema(description = "Local da ordem", example = "Rua A 222 Campo Grande RJ")
    private String local;

    @Schema(description = "Data do fim do serviço", example = "2024-05-09T24:00:00")
    private LocalDateTime endAt;

    @Schema(description = "Preço base do serviço", example = "29.99")
    private Double price;
}
