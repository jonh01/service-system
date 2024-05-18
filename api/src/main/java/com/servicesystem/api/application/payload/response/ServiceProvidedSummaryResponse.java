package com.servicesystem.api.application.payload.response;

import java.util.UUID;

import com.servicesystem.api.domain.models.enums.StatusService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvidedSummaryResponse {

    private UUID id;
    private String name;
    private String image;
    private String description;
    private StatusService status;
    private User user;
    private Category category;
    private Integer numReviews;
    private Integer sumReviews;

    @Data
	@NoArgsConstructor
	@AllArgsConstructor
    private static class User{
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
