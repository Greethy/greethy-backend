package com.greethy.user.api.rest.controller.user;

import com.greethy.user.api.rest.dto.UserDto;
import com.greethy.user.api.rest.dto.response.ErrorResponse;
import com.greethy.user.api.rest.dto.response.UsersLookupResponse;
import com.greethy.user.core.port.in.query.FindAllUserQuery;
import com.greethy.user.core.port.in.query.FindUserByIdQuery;
import com.greethy.user.core.port.in.query.FindUserByUsernameOrEmailQuery;
import com.greethy.user.core.port.in.query.GetAllUserWithPageableQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryExecutionException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Class {@code UserQueriesEndpointHandler} represents the handler for handling user-related queries in a RESTful API,
 * it contains methods to handle queries. This handler interacts with the application's business logic through the
 * Axon Framework's ReactorQueryGateway, which allows for querying data in a reactive manner.
 *
 * @author Kien N.Thanh
 */
@Component
@RequiredArgsConstructor
public class UserQueriesEndpointHandler {

    private final ModelMapper mapper;

    private final String DEFAULT_PAGE_VALUE = "0";

    private final String DEFAULT_SIZE_VALUE = "10";

    private final ReactorQueryGateway reactiveQueryGateway;

    public Mono<ServerResponse> findAllUser() {
        return reactiveQueryGateway.query(new FindAllUserQuery(), ResponseTypes.instanceOf(UsersLookupResponse.class))
                .switchIfEmpty(Mono.just(UsersLookupResponse.builder().build()))
                .flatMap(this::handleQueryResponse);
    }

    public Mono<ServerResponse> findAllUserWithPageable(ServerRequest serverRequest) {
        Integer page = serverRequest.queryParam("page")
                .filter(pageValue -> !pageValue.isEmpty())
                .or(() -> Optional.of(DEFAULT_PAGE_VALUE))
                .map(Integer::valueOf).get();
        Integer size = serverRequest.queryParam("size")
                .filter(sizeValue -> !sizeValue.isEmpty())
                .or(() -> Optional.of(DEFAULT_SIZE_VALUE))
                .map(Integer::valueOf).get();
        var query = GetAllUserWithPageableQuery.builder()
                .page(page).size(size).sort("userId")
                .build();
        return reactiveQueryGateway.query(query, ResponseTypes.instanceOf(UsersLookupResponse.class))
                .switchIfEmpty(Mono.just(UsersLookupResponse.builder().build()))
                .flatMap(this::handleQueryResponse);
    }

    private Mono<ServerResponse> handleQueryResponse(UsersLookupResponse response) {
        if (response.getUsers().isEmpty()) {
            return ServerResponse.status(HttpStatus.NO_CONTENT)
                    .body(Mono.just("No user found"), String.class);
        }
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response);
    }

    public Mono<ServerResponse> findUserById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("user_id"))
                .map(userId -> FindUserByIdQuery.builder().userId(userId).build())
                .flatMap(query -> reactiveQueryGateway.query(query, ResponseTypes.instanceOf(UserDto.class)))
                .flatMap(userDto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(userDto))
                .onErrorResume(this::handleException);
    }

    public Mono<ServerResponse> findUserByUsernameOrEmail(ServerRequest serverRequest) {
        return Mono.just(serverRequest.queryParam("username_or_email")
                .orElse(""))
                .map(usernameOrEmail -> FindUserByUsernameOrEmailQuery.builder()
                        .usernameOrEmail(usernameOrEmail)
                        .build())
                .flatMap(query -> reactiveQueryGateway.query(query, ResponseTypes.instanceOf(UserDto.class)))
                .flatMap(userDto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(userDto))
                .onErrorResume(this::handleException);
    }

    public Mono<ServerResponse> checkIfUserEmailExists(ServerRequest serverRequest) {
//        return Mono.just(serverRequest.pathVariable("user_id"))
//                .flatMap();
        return null;
    }

    private Mono<ServerResponse> handleException(Throwable throwable) {
        if (throwable instanceof QueryExecutionException exception) {
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

}
