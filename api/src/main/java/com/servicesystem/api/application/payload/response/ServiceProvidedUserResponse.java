package com.servicesystem.api.application.payload.response;

import java.util.UUID;

import com.servicesystem.api.domain.models.enums.StatusService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvidedUserResponse {

    private UUID id;
    private String name;
    private String image;
    private StatusService status;

}
