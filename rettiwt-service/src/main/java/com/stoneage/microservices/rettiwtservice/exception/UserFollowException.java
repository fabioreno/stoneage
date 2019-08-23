package com.stoneage.microservices.rettiwtservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings(value = "serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserFollowException extends RuntimeException 
{
    public UserFollowException(String message) 
    {
        super(message);
    }
}
