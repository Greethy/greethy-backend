package com.greethy.personal.application.rest.controller;

import com.greethy.core.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
public class GlobalReactiveExceptionHandler extends AbstractErrorWebExceptionHandler {

    private final Map<Class<? extends Exception>, HttpStatus> exceptionStatusCodesMap;

    private final HttpStatus defaultHttpStatus;

    public GlobalReactiveExceptionHandler(ErrorAttributes errorAttributes,
                                          WebProperties.Resources resources,
                                          ApplicationContext applicationContext,
                                          Map<Class<? extends Exception>, HttpStatus> exceptionStatusCodesMap,
                                          HttpStatus httpStatus) {
        super(errorAttributes, resources, applicationContext);
        this.exceptionStatusCodesMap = exceptionStatusCodesMap;
        this.defaultHttpStatus = httpStatus;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {

        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest serverRequest) {
        Throwable error = getError(serverRequest);
        HttpStatus status = (error instanceof Exception exception)
                ? exceptionStatusCodesMap.getOrDefault(exception.getClass(), defaultHttpStatus)
                : HttpStatus.INTERNAL_SERVER_ERROR;
        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromValue(ErrorResponse.builder()
                                .code(status.value())
                                .status(status.name())
                                .message(error.getMessage())
                                .build()
                        ))
                .log();
    }
}
