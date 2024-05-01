package com.servicesystem.api.application.payload.update;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingUpdate {

    private String note;
    private String comment;
    private Set<String> images = new HashSet<>();
}
