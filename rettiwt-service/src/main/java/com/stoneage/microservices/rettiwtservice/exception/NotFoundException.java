package com.stoneage.microservices.rettiwtservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings(value = "serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException 
{
    public NotFoundException(String message) 
    {
        super(message);
    }
}
