package com.servicesystem.api.application.payload.response;

import java.util.UUID;

import com.servicesystem.api.domain.models.Metrics;
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
    private User user;
    private Metrics metrics;

    @Data
	@NoArgsConstructor
	@AllArgsConstructor
    private static class User{
	private String name;
    private String image;
    }

}
