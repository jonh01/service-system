package com.servicesystem.api.application.payload.update;

import java.util.Set;
import java.util.HashSet;

import com.servicesystem.api.domain.models.enums.RegisteredUserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdate {

	private String name;
	private String email;
    private String phone;
    private String image;
    private Set<RegisteredUserType> type = new HashSet<>();

}
