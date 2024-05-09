package com.servicesystem.api.application.payload.insert;

import java.util.Set;
import java.util.HashSet;

import com.servicesystem.api.domain.models.enums.RegisteredUserType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInsert {

    @NotBlank
	private String name;

    @NotBlank
	private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String image;
    
    private Set<RegisteredUserType> type = new HashSet<>(); 

}
