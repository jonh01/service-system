package com.servicesystem.api.application.payload.update;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdate {

    private LocalDateTime endAt;
    private Double price;
}
