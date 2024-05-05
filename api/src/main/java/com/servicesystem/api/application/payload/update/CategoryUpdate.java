package com.servicesystem.api.application.payload.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdate {

	private String name;
	private String description;
	private Double price;
}
