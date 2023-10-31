package com.servicesystem.api.domain.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(generator = "UUID")
	private UUID id;
	private String name;
	private String description;
}
