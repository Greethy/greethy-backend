package com.greethy.userquery.api.rest.handler;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.core.api.handler.ExceptionHandler;
import com.greethy.userquery.domain.service.UserQueryService;
import com.greethy.usercommon.dto.request.query.GetUserByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@EndpointHandler
@RequiredArgsConstructor
public class UserQueryHandler {

    private final UserQueryService userQueryService;

    private final ExceptionHandler exceptionHandler;

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("user-id"))
                .map(GetUserByIdQuery::new)
                .flatMap(userQueryService::getUserById)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getByUsernameOrEmail(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> getAllByPagination(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> checkIfEmailIsExisted(ServerRequest serverRequest) {
        return null;
    }

}
