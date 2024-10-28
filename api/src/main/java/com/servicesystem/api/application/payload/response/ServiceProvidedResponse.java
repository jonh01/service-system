package com.servicesystem.api.application.payload.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.servicesystem.api.domain.models.enums.StatusService;

import java.util.HashSet;
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
    private StatusService status;
    private Set<String> localAction = new HashSet<>();
    private User user;
    private Category category;
    private LocalDateTime createdAt;
    private Integer numReviews;
    private Integer sumReviews;
    private Set<ReviewsNoteResponse> numReviewsNote = new HashSet<>();

    @Data
	@NoArgsConstructor
	@AllArgsConstructor
    private static class User{
        private UUID id;
	    private String name;
        private String image;
        private String phone;
    }

    @Data
	@NoArgsConstructor
	@AllArgsConstructor
    private static class Category{
        private UUID id;
	    private String name;
    }

}
