package com.greethy.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Base exception class for handling runtime exceptions in the application.
 * Extends {@code RuntimeException}. This exception provides a standard way
 * to represent and handle exceptions with an associated HTTP status code.
 *
 * @author ThanhKien
 */
@Getter
public class BaseException extends RuntimeException {

    private final HttpStatus status;

    /**
     * Constructs a new {@code BaseException} with the specified message and HTTP status.
     *
     * @param status The HTTP status code to be associated with the exception.
     * @param message A description of the exception.
     */
    public BaseException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }

}
