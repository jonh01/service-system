package com.servicesystem.api.application.payload.insert;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInsert {

    @NotBlank
    private String description;

    @NotBlank
    private Double price;

    @NotBlank
    private UUID userId;

    @NotBlank
    private UUID serviceId;
}
