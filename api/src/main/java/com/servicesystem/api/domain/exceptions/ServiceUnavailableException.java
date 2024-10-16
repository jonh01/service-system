package com.servicesystem.api.domain.exceptions;

public class ServiceUnavailableException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public ServiceUnavailableException(String message){
        super(message);
    }

    public ServiceUnavailableException(String mensage, Throwable cause) {
        super(mensage, cause);
    }
}
