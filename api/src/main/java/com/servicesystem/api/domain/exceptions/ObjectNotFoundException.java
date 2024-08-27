package com.servicesystem.api.domain.exceptions;

public class ObjectNotFoundException  extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String message){
        super(message);
    }

    public ObjectNotFoundException(String mensage, Throwable cause) {
        super(mensage, cause);
    }
    
}
