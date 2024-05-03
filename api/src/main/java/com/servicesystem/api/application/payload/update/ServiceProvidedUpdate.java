package com.servicesystem.api.application.payload.update;

import com.servicesystem.api.domain.models.enums.StatusService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvidedUpdate {

    private String name;
    private StatusService status;
    private String image;
    private String localAtuacao;
    private String description;
    
}
