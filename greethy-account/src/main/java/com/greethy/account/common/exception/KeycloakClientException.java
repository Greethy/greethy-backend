package com.greethy.account.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * A Runtime exception thrown when something goes wrong in an API call to Keycloak Service.
 *
 * @author ThanhKien
 * @since 1.0.0
 */
@Getter
public class KeycloakClientException extends RuntimeException {

    private final HttpStatusCode status;

    private KeycloakClientException(String message, HttpStatusCode status) {
        super(message);
        this.status = status;
    }

    /**
     * Creates a new keycloak client exception with the specified message and HTTP status.
     *
     * @param message the message to describe this exception
     * @param status the return HTTP status of exception
     * @return a keycloak client exception instance
     */
    public static KeycloakClientException of(final String message, final HttpStatusCode status) {
        return new KeycloakClientException(message, status);
    }

}
