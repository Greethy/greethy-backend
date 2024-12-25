package com.greethy.account.common.exception;

import com.greethy.core.model.response.DataResponse;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class ExceptionHandler {

    public Mono<ServerResponse> handle(KeycloakClientException ex, ServerRequest request) {
        var title = ex.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2");
        var problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatus(), ex.getMessage());
        problemDetail.setInstance(URI.create(request.path()));
        problemDetail.setTitle(title);
        var responseBody = DataResponse.<ProblemDetail>builder()
                .data(problemDetail).success(false).build();
        return ServerResponse.status(ex.getStatus()).bodyValue(responseBody);
    }

    public Mono<ServerResponse> handle(InvalidInputException ex, ServerRequest request) {
        return null;
    }


}
