package com.greethy.auth.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when an authentication token is invalid.
 * Extends {@link BaseException}.
 *
 * @author ThanhKien
 */
public class InvalidTokenException extends BaseException {

    /**
     * Constructs an {@code InvalidTokenException} with the specified HTTP status and message.
     *
     * @param status  The HTTP status code associated with the exception.
     * @param message A description of the exception.
     */
    public InvalidTokenException(HttpStatus status, String message){
        super(status, message);
    }

}
