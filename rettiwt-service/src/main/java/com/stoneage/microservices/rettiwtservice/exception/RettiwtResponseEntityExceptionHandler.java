package com.stoneage.microservices.rettiwtservice.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RettiwtResponseEntityExceptionHandler extends 
    ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, 
            WebRequest request) throws Exception
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
                ex.getMessage(), request.getDescription(false));
        
        return new ResponseEntity<>(exceptionResponse, INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(
            NotFoundException ex, WebRequest request) throws Exception
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
                ex.getMessage(), request.getDescription(false));
        
        return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
    }
    
    @ExceptionHandler(value = UserFollowException.class)
    public final ResponseEntity<Object> handleUserFollowException(
            UserFollowException ex, WebRequest request) throws Exception
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
                ex.getMessage(), request.getDescription(false));
        
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, 
            HttpStatus status, WebRequest request) 
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
                "Validation Failed", ex.getBindingResult().toString());
        
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }
}
