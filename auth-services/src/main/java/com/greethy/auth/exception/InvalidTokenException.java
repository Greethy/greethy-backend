package com.greethy.auth.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends BaseException {

    public InvalidTokenException(HttpStatus status, String message){
        super(status, message);
    }

}
