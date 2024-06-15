package com.greethy.nutritionquery.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritionquery.domain.service.BodySpecQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class BodySpecQueryHandler {

    private final ExceptionHandler exceptionHandler;

    private final BodySpecQueryService bodySpecService;

    public Mono<ServerResponse> getBodySpecById(ServerRequest request) {
        return Mono.fromSupplier(() -> request.pathVariable("body-spec-id"))
                .map(GetByIdQuery::new)
                .flatMap(bodySpecService::findBodySpecById)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

}
