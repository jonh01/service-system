package com.servicesystem.api.domain.models;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsNotePK  implements Serializable{

    private Integer note;
    private UUID serviceProvidedId;
    
}
