package com.servicesystem.api.application.payload.response;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {

    private UUID id;
    private Integer note;
    private String comment;
    private Set<String> images = new HashSet<>();
    private UUID userId;
    private UUID serviceProvidedId;
    private LocalDateTime createdAt;
}
