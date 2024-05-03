package com.servicesystem.api.domain.models;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metrics {
    
        private Double average;
        private Integer numReviews;
        private HashMap<Integer,Integer> numReviewsNote = new HashMap<>();

        public void addNumReviewsNote(int num){
            numReviewsNote.compute(num, (k, v) -> (v == null) ? 1 : v+1);
        }
}
