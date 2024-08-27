package com.servicesystem.api.domain.exceptions;

public class BusinessException extends RuntimeException{
    	
    private static final long serialVersionUID = 1L;

	public BusinessException(String mensage){
        super(mensage);
    }

    public BusinessException(String mensage, Throwable cause) {
        super(mensage, cause);
    }
}
