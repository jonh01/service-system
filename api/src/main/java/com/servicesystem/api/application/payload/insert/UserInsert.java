package com.servicesystem.api.application.payload.insert;

import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;

import java.util.HashSet;

import com.servicesystem.api.domain.models.enums.RegisteredUserType;
import com.servicesystem.api.domain.validations.Phone;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInsert {

    @Schema(description = "Nome do usuário", example = "João")
    @NotBlank
	private String name;

    @Schema(description = "Email do usuário", example = "joao@email.com")
    @NotBlank
    @Email
	private String email;

    @Schema(description = "CPF do usuário", example = "418.717.690-00")
    @NotBlank
    @CPF
    private String cpf;

    @Schema(description = "Telefone do usuário", example = "(21)91234-5678")
    @NotBlank
    @Phone
    private String phone;

    @Schema(description = "Imagem do usuário", example = "Imagem em base 64")
    private String image;
    
    private Set<RegisteredUserType> type = new HashSet<>(); 

}
