package com.greethy.auth.exception;

import com.greethy.auth.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for RESTful APIs. This class is annotated
 * with {@code @RestControllerAdvice}, indicating that it contains
 * global exception handling logic for controllers.
 *
 * @author ThanhKien
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Handles exceptions of type {@code DuplicateUniqueFieldException}.
     * It handles exceptions to the specified type, and {@code @ResponseStatus}
     * to set the HTTP status code for the response.
     *
     * @param ex The instance of {@code DuplicateUniqueFieldException} to be handled.
     * @return An {@code ErrorResponse} containing the HTTP status code and error message.
     */
    @ExceptionHandler(DuplicateUniqueFieldException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEntityNotFoundExceptions(DuplicateUniqueFieldException ex){
        return new ErrorResponse(ex.getStatus(), ex.getMessage());
    }

}
