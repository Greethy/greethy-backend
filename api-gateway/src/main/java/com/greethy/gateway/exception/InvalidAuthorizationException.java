package com.greethy.gateway.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidAuthorizationException extends RuntimeException {

    public InvalidAuthorizationException(String message){
        super(message);
    }


}
