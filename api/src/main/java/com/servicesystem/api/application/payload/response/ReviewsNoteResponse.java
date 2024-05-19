package com.servicesystem.api.application.payload.response;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsNoteResponse {
    
    private Integer note;
    private UUID serviceProvidedId;
    private Integer numReviews;
}
