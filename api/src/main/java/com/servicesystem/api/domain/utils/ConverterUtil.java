package com.servicesystem.api.domain.utils;

import java.util.UUID;

import com.servicesystem.api.domain.exceptions.BusinessException;

public class ConverterUtil {

    private ConverterUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static UUID convertStringForUUID(String value){

        UUID uuid;

        try {
            uuid = UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("O id informado não segue o padrão correto!");
        }
        return uuid;
    }
    
}