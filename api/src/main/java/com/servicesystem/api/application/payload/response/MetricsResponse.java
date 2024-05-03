package com.servicesystem.api.application.payload.response;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetricsResponse {
    
        private Double average;
        private Integer numReviews;
        private HashMap<Integer,Integer> numReviewsNote = new HashMap<>();
}
