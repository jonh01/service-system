package com.servicesystem.api.application.payload.update;

import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingUpdate {

    @Schema(description = "Nota para o serviço prestado", example = "1", minimum = "1", maximum = "5")
    @Min(value = 1, message = "Só são aceitos valores de 1 - 5")
    @Max(value = 5, message = "Só são aceitos valores de 1 - 5")
    private Integer note;
    
    @Schema(description = "Comentário sobre serviço prestado", example = "Ótimo serviço")
    private String comment;

    private Set<String> images = new HashSet<>();
}
