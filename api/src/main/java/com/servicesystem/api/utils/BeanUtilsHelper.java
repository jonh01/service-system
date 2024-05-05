package com.servicesystem.api.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BeanUtilsHelper {

   public static void copyNonNullProperties(Object dest, Object source) {
        try {
            Map<String, String> properties = BeanUtils.describe(source);
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                String propName = entry.getKey();
                String value = entry.getValue();

                if (value != null) {
                    BeanUtils.copyProperty(dest, propName, value);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Falha ao copiar propriedades: ", e);
        }
    }
    
}
