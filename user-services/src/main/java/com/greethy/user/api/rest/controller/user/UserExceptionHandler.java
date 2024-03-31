package com.greethy.user.api.rest.controller.user;

import com.greethy.core.api.response.ErrorResponse;
import com.greethy.core.domain.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandExecutionException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserExceptionHandler {

    private final ModelMapper mapper;

    Mono<ServerResponse> handlingCommandException(Throwable throwable) {
        if (throwable instanceof CommandExecutionException exception) {
            ErrorResponse response = exception.getDetails()
                    .map(detail -> mapper.map(detail, ErrorResponse.class))
                    .orElse(ErrorResponse.builder()
                            .message("Something wrong with Server")
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .build()
                    );
            return ServerResponse.status(response.getStatus())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(response);
        }
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

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
    Mono<ServerResponse> handlingQueryException(Throwable throwable) {
        if (throwable instanceof BaseException exception) {
            return ServerResponse.status(exception.getStatus())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(ErrorResponse.builder()
                            .message(exception.getMessage())
                            .status(exception.getStatus())
                            .build());
        }
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

}
