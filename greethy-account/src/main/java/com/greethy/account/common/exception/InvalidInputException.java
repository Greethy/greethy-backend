package com.greethy.account.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * A Runtime exception thrown when a user's input is violated constraint
 *
 * @author ThanhKien
 * @since 1.0.0
 */
@Getter
public class InvalidInputException extends RuntimeException {
    private final HttpStatusCode status;

    public InvalidInputException(String message, HttpStatusCode status) {
        super(message);
        this.status = status;
    }

    /**
     * Creates a new invalid input exception with the specified message and HTTP status.
     *
     * @param message the message to describe this exception
     * @param status the return status of exception
     * @return an invalid input exception instance
     */
    public static InvalidInputException of(final String message, final HttpStatusCode status) {
        return new InvalidInputException(message, status);
    }
}
