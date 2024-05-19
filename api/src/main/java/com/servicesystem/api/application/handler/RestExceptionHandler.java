package com.servicesystem.api.application.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.servicesystem.api.domain.exceptions.BusinessException;
import com.servicesystem.api.domain.exceptions.ObjectNotFoundException;
import com.servicesystem.api.domain.exceptions.ServiceUnavailableException;
import com.servicesystem.api.domain.models.StandardError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {
    
    @Autowired
    private MessageSource messageSource;
    
    // erros para regra de negócio
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> handleBusinessException(BusinessException exception, HttpServletRequest request){

        StandardError err = new StandardError();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        err.setMessage(exception.getMessage());
        err.setPath(request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    // servidor não funcionando
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<StandardError> handleServiceUnavailableException(ServiceUnavailableException exception, HttpServletRequest request){

        StandardError err = new StandardError();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        err.setError(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
        err.setMessage(exception.getMessage());
        err.setPath(request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(err);
    }

    // erros para objeto não encontrado
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handleObjectNotFoundException(ObjectNotFoundException exception, HttpServletRequest request){

    	StandardError err = new StandardError();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        err.setMessage(exception.getMessage());
        err.setPath(request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    // erros para recursos não encontrado
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<StandardError> handleResourceNotFoundException(NoResourceFoundException exception, HttpServletRequest request){
    
        StandardError err = new StandardError();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        err.setMessage("URL não encontrada!");
        err.setPath(request.getRequestURI());
            
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    // erros na requisição: verificação dos campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> handleValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
		
        List<StandardError.Field> fields = new ArrayList<>();

        for(ObjectError error : e.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error,LocaleContextHolder.getLocale());
            fields.add(new StandardError.Field(name, message));
        }

        StandardError err = new StandardError(
        		LocalDateTime.now(), 
        		HttpStatus.UNPROCESSABLE_ENTITY.value(),
        		HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), 
        		"Um ou mais campos estão inválidos", 
        		request.getRequestURI(),
        		fields
        		);
	
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}
}
