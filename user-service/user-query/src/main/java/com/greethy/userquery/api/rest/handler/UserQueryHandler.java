package com.greethy.userquery.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.common.infra.util.ServerRequestUtil;
import com.greethy.usercommon.dto.request.query.GetAllUsersByPaginationQuery;
import com.greethy.usercommon.dto.request.query.GetCurrentUserProfileQuery;
import com.greethy.usercommon.dto.request.query.GetUserByIdQuery;
import com.greethy.usercommon.dto.request.query.GetUserByUsernameOrEmailQuery;
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
                .map(GetUserByIdQuery::new)
                .flatMap(userQueryService::getUserById)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getByUsernameOrEmail(ServerRequest serverRequest) {
        return Mono.justOrEmpty(serverRequest.queryParam("username-or-email"))
                .map(GetUserByUsernameOrEmailQuery::new)
                .flatMap(userQueryService::getUserByUsernameOrEmail)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getAllByPagination(ServerRequest serverRequest) {
        Integer offset = ServerRequestUtil.getIntegerValue(serverRequest, "offset", "0");
        Integer limit = ServerRequestUtil.getIntegerValue(serverRequest, "limit", "10");
        Flux<UserResponse> fluxResponse = Flux.just((new GetAllUsersByPaginationQuery(offset, limit)))
                .flatMap(userQueryService::getAllUsersByPagination);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxResponse, UserResponse.class);
    }

    public Mono<ServerResponse> getCurrentUser(ServerRequest serverRequest) {
        return serverRequest.principal()
                .map(principal -> new GetCurrentUserProfileQuery(principal.getName()))
                .flatMap(userQueryService::getUserProfileByUsernameOrEmail)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getIdentity(ServerRequest serverRequest) {
        return Mono.justOrEmpty(serverRequest.queryParam("username-or-email"))
                .map(GetUserByUsernameOrEmailQuery::new)
                .flatMap(userQueryService::getUserIdentityByUsernameOrEmail)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> checkIfEmailIsExisted(ServerRequest serverRequest) {
        return null;
    }
}
