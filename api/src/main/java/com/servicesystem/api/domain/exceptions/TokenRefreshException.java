package com.servicesystem.api.domain.exceptions;

public class TokenRefreshException extends RuntimeException  {
            	
    private static final long serialVersionUID = 1L;

	public TokenRefreshException(String mensage){
        super(mensage);
    }

    public TokenRefreshException(String mensage, Throwable cause) {
        super(mensage, cause);
    }
}
