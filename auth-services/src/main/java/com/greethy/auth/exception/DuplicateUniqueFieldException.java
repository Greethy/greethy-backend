package com.greethy.auth.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception class representing a conflict due to duplicate values in unique fields.
 * Extends {@code BaseException}. This exception is thrown when attempting to create or update
 * a resource, and the provided data violates the uniqueness constraint of certain fields.
 *
 * @author ThanhKien
 */
public class DuplicateUniqueFieldException extends BaseException {

    /**
     * Constructs a new {@code DuplicateUniqueFieldException} with the specified HTTP status and message.
     *
     * @param status The HTTP status code associated with the exception.
     * @param message A description of the exception.
     */
    public DuplicateUniqueFieldException(HttpStatus status, String message){
        super(status, message);
    }

}
