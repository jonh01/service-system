package com.servicesystem.api.domain.models;

import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metrics {

    private Integer numReviews;
    private Integer sumReviews;

    @JsonIgnore
    @ElementCollection
	@CollectionTable(name="tb_metrics_num_reviews_note")
    @MapKeyColumn(name = "note")
    @Column(name = "num_reviews")
    private HashMap<Integer, Integer> numReviewsNote = new HashMap<>();

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "fk_service_provided_id")
    private ServiceProvided serviceProvided;

    public void addNumReviewsNote(int num) {
        numReviewsNote.compute(num, (k, v) -> (v == null) ? 1 : v + 1);
    }

}
