package com.servicesystem.api.application.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleTokenErrorResponse {
    
    private String error;
    private String error_description;
}
