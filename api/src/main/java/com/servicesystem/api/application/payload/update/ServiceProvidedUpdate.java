package com.servicesystem.api.application.payload.update;

import java.util.HashSet;
import java.util.Set;

import com.servicesystem.api.domain.models.enums.StatusService;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvidedUpdate {

    @Schema(description = "Nome do serviço", example = "Conserto de Notebooks")
    private String name;

    @Schema(description = "Status do serviço", example = "Active")
    private StatusService status;

    
    private String image;

    @Schema(description = "Descrição do serviço", example = "Conserto e limpeza de Notebooks")
    private String description;

    @Schema(description = "Locais para a prestação de serviços", example = "Campo grande")
    private Set<String> localAction = new HashSet<>();
    
}
