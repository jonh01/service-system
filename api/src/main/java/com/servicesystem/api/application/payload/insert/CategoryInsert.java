package com.servicesystem.api.application.payload.insert;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInsert {

	@Schema(description = "Nome da categoria", example = "Conserto de Notebooks")
	@NotBlank
	private String name;

	@Schema(description = "Descrição da categoria", example = "Conserto e limpeza de notebooks")
	@NotBlank
	private String description;
}
