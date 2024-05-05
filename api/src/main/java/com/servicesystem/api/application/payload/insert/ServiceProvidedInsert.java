package com.servicesystem.api.application.payload.insert;

import java.util.UUID;
import java.util.Set;
import java.util.HashSet;
import com.servicesystem.api.domain.models.enums.StatusService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvidedInsert {

    @NotBlank
    private String name;

    private StatusService status;

    private String image;

    @NotBlank
    private String description;

    @NotBlank
    private Set<String> localAction = new HashSet<>();

    @NotBlank
    private UUID userId;

    @NotBlank
    private UUID categoryId;

}
