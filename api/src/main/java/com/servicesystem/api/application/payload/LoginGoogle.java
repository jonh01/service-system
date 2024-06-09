package com.servicesystem.api.application.payload;

import com.servicesystem.api.application.payload.insert.UserInsert;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginGoogle {

    @Schema(description = "Usuário", example = "{\"name\":\"João\", \"email\":\"joao@email.com\", \"cpf\":\"418.717.690-00\", \"phone\": \"(21)91234-5678, \"image\": \"Imagem em base 64\", \"type\": [\"Client\"]} ")
    @Valid
    private UserInsert user;

    @Schema(description = "Token Google", example = "Token_do_google")
    @NotBlank
	private String googleToken;
    
}
