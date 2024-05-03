package com.servicesystem.api.application.services;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.servicesystem.api.domain.models.Metrics;
import com.servicesystem.api.domain.models.Rating;
import com.servicesystem.api.domain.models.ServiceProvided;

@Service
public class ServiceProvidedService {




    // adicionar métricas de avaliações aos serviços
    private void addMetrics(ServiceProvided serviceProvided) {

        // soma todas as notas
        Integer sum = serviceProvided.getRatings().stream().collect(Collectors.summingInt(Rating::getNote));
        Metrics metrics = new Metrics();

        // adiciona o número e avaliações
        metrics.setNumReviews(serviceProvided.getRatings().size());

        // adiciona as medias de avaliações
        metrics.setAverage(((double) sum) / metrics.getNumReviews());

        // adiciona quanto cada nota tem
        serviceProvided.getRatings().forEach(ratings -> metrics.addNumReviewsNote(ratings.getNote()));

        // adiciona as metricas no serviço
        serviceProvided.setMetrics(metrics);
    }

}
