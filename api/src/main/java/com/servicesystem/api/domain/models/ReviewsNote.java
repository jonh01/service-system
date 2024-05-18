package com.servicesystem.api.domain.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_reviews_note")
@IdClass(ReviewsNotePK.class)
public class ReviewsNote {

    @Id
    private Integer note;

    @Id
    @Column(name = "fk_service_provided_id")
    private UUID serviceProvidedId;
    
    @Column(name = "num_reviews")
    private Integer numReviews;

}
