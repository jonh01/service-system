package com.servicesystem.api.application.payload.insert;

import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

import com.servicesystem.api.domain.models.enums.RegisteredUserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInsert {

    private UUID id;
	private String name;
	private String email;
    private String phone;
    private String image;
    private Set<RegisteredUserType> type = new HashSet<>(); 

}
