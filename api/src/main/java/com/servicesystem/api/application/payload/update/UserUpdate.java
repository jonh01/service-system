package com.servicesystem.api.application.payload.update;

import java.util.Set;
import java.util.HashSet;

import com.servicesystem.api.domain.models.enums.RegisteredUserType;
import com.servicesystem.api.domain.validations.Phone;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdate {

    @Schema(description = "Nome do usuário", example = "João")
	private String name;

    @Schema(description = "Email do usuário", example = "joao@email.com")
    @Email
	private String email;

    @Schema(description = "Telefone do usuário", example = "(21)91234-5678")
    @Phone
    private String phone;
    
    @Schema(description = "Imagem do usuário", example = "Imagem em base 64")
    private String image;
    
    private Set<RegisteredUserType> type = new HashSet<>();

}
