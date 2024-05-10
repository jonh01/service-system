package com.servicesystem.api.application.payload.insert;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingInsert {

    @Schema(description = "Nota para o serviço prestado", example = "1", minLength = 1, maxLength = 5)
    @NotNull
    @Min(value = 1, message = "Só são aceitos valores de 1 - 5")
    @Max(value = 5, message = "Só são aceitos valores de 1 - 5")
    private Integer note;
    
    @Schema(description = "Comentário sobre serviço prestado", example = "Ótimo serviço")
    private String comment;
    
    private Set<String> images = new HashSet<>();

    @NotBlank
    private UUID userId;

    @NotBlank
    private UUID serviceProvidedId;
}
