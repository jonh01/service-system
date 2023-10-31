package com.servicesystem.api.domain.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_service")
public class Service {

    @Id
    @GeneratedValue(generator = "UUID")
	private UUID id;
}
