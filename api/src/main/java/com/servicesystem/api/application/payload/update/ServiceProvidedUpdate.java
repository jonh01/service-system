package com.servicesystem.api.application.payload.update;

import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvidedUpdate {
    
    private String image;

    @Schema(description = "Descrição do serviço", example = "Conserto e limpeza de Notebooks")
    private String description;

    @Schema(description = "Locais para a prestação de serviços", example = "Campo grande")
    private Set<String> localAction = new HashSet<>();
    
}
