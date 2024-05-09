package com.servicesystem.api.application.payload.insert;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingInsert {

    @NotBlank
    @Min(value = 1, message = "Só são aceitos valores de 1 - 5")
    @Max(value = 5, message = "Só são aceitos valores de 1 - 5")
    private Short note;
    
    private String comment;
    private Set<String> images = new HashSet<>();

    @NotBlank
    private UUID userId;

    @NotBlank
    private UUID serviceProvidedId;
}
