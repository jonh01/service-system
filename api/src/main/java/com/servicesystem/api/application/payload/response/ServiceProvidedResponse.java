package com.servicesystem.api.application.payload.response;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.servicesystem.api.domain.models.Metrics;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvidedResponse {

    private UUID id;
    private String name;
    private String image;
    private String description;
    private Set<String> localAction = new HashSet<>();
    private User user;
    private List<RatingResponse> ratings;
    private Metrics metrics;
    private LocalDateTime createdAt;

    @Data
	@NoArgsConstructor
	@AllArgsConstructor
    private static class User{
	private String name;
    private String image;
    }

}
