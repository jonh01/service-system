package com.servicesystem.api.application.payload.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdate {

	@Schema(description = "Nome da categoria", example = "Conserto de Notebooks")
	private String name;

	@Schema(description = "Descrição da categoria", example = "Conserto e limpeza de notebooks")
	private String description;
}
