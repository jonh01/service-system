package com.servicesystem.api.application.payload.update;

import java.util.HashSet;
import java.util.Set;

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
    private String description;
    private Set<String> localAction = new HashSet<>();
    
}
