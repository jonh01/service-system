package com.servicesystem.api.domain.exceptions;

public class JWTCreationException extends RuntimeException {
        	
    private static final long serialVersionUID = 1L;

	public JWTCreationException(String mensage){
        super(mensage);
    }

    public JWTCreationException(String mensage, Throwable cause) {
        super(mensage, cause);
    }
}
