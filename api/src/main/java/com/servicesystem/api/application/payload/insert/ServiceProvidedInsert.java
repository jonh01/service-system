package com.servicesystem.api.application.payload.insert;

import java.util.UUID;
import java.util.Set;
import java.util.HashSet;
import com.servicesystem.api.domain.models.enums.StatusService;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvidedInsert {

    @Schema(description = "Nome do serviço", example = "Conserto de Notebooks")
    @NotBlank
    private String name;

    @Schema(description = "Status do serviço", example = "Active")
    private StatusService status;

    private String image;

    @Schema(description = "Descrição do serviço", example = "Conserto e limpeza de Notebooks")
    @NotBlank
    private String description;

    @Schema(description = "Locais para a prestação de serviços", example = "Campo grande")
    @NotBlank
    private Set<String> localAction = new HashSet<>();

    @NotBlank
    private UUID userId;

    @NotBlank
    private UUID categoryId;

}
