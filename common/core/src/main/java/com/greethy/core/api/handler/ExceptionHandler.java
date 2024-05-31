package com.greethy.core.api.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.greethy.core.api.response.ErrorResponse;
import com.greethy.core.domain.exception.BaseException;

import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ExceptionHandler {

    /**
     * Handles exceptions that occur during request processing.
     * <p>
     * This method receives a throwable and checks if it's an instance of {@link BaseException}.
     * If it is, it generates an HTTP response with the error details specified in the exception.
     * Otherwise, it returns an HTTP 500 Internal Server Error response.
     *
     * @param throwable The throwable representing the exception.
     * @return A {@link Mono} representing the server response with error details.
     */
    public Mono<ServerResponse> handlingException(Throwable throwable) {
        log.error("Some exception have pop up: {}", throwable.getMessage());
        if (throwable instanceof BaseException exception) {
            return ServerResponse.status(exception.getStatus())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(ErrorResponse.builder()
                            .message(exception.getMessage())
                            .status(exception.getStatus())
                            .build());
        }
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .bodyValue(throwable.getMessage());
    }
}
