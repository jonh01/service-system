package com.servicesystem.api.application.payload.insert;

import java.util.UUID;

import java.util.Set;
import java.util.HashSet;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @Schema(description = "Imagem do usuário", example = "Imagem em base 64")
    private String image;

    @Schema(description = "Descrição do serviço", example = "Conserto e limpeza de Notebooks")
    @NotBlank
    private String description;

    @Schema(description = "Locais para a prestação de serviços", example = "[\"Campo grande\"]")
    @NotEmpty
    private Set<String> localAction = new HashSet<>();

    private User user;

    private Category category;

    @Data
	@NoArgsConstructor
	@AllArgsConstructor
    public static class User{
        @NotNull
        private UUID id;
    }

    @Data
	@NoArgsConstructor
	@AllArgsConstructor
    public static class Category{
        @NotNull
        private UUID id;
    }

}
