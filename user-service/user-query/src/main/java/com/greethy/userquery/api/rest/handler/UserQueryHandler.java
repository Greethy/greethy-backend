package com.greethy.userquery.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.common.infra.util.ServerRequestUtil;
import com.greethy.usercommon.dto.request.query.*;
import com.greethy.usercommon.dto.response.UserResponse;
import com.greethy.userquery.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@EndpointHandler
@RequiredArgsConstructor
public class UserQueryHandler {

    private final UserQueryService userQueryService;

    private final ExceptionHandler exceptionHandler;

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("user-id"))
                .map(GetByIdQuery::new)
                .flatMap(userQueryService::getUserById)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getByUsernameOrEmail(ServerRequest serverRequest) {
        return Mono.justOrEmpty(serverRequest.queryParam("username-or-email"))
                .map(GetByUsernameOrEmailQuery::new)
                .flatMap(userQueryService::getUserByUsernameOrEmail)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getAllByPagination(ServerRequest serverRequest) {
        Integer offset = ServerRequestUtil.getQueryParamIntegerValue(serverRequest, "offset", "0");
        Integer limit = ServerRequestUtil.getQueryParamIntegerValue(serverRequest, "limit", "10");
        String sort = ServerRequestUtil.getQueryParamStringValue(serverRequest, "sort", "+id");
        Flux<UserResponse> fluxResponse = Flux.just((new GetByPaginationQuery(offset, limit, sort)))
                .flatMap(userQueryService::getAllUsersByPagination);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxResponse, UserResponse.class)
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getCurrentUser(ServerRequest serverRequest) {
        return serverRequest.principal()
                .map(principal -> new GetByUsernameOrEmailQuery(principal.getName()))
                .flatMap(userQueryService::getUserProfileByUsernameOrEmail)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getIdentity(ServerRequest serverRequest) {
        return Mono.justOrEmpty(serverRequest.queryParam("username-or-email"))
                .map(GetByUsernameOrEmailQuery::new)
                .flatMap(userQueryService::getIdentityByUsernameOrEmail)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getAllUserIds() {
        return userQueryService.getAllUserIds()
                .collectList()
                .flatMap(responses -> ServerResponse.ok().bodyValue(responses));
    }

    public Mono<ServerResponse> getUserIdByUsername(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("username"))
                .map(GetByUsernameOrEmailQuery::new)
                .flatMap(userQueryService::getUserIdByUsername)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);

    }
}
