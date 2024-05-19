package com.servicesystem.api.application.payload.response;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Category category;
    private LocalDateTime createdAt;
    private Integer numReviews;
    private Integer sumReviews;

    @Data
	@NoArgsConstructor
	@AllArgsConstructor
    private static class User{
        private UUID id;
	    private String name;
        private String image;
    }

    @Data
	@NoArgsConstructor
	@AllArgsConstructor
    private static class Category{
        private UUID id;
	    private String name;
    }

}
