package com.greethy.user.api.rest.controller.handler;

import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.greethy.core.api.handler.ExceptionHandler;
import com.greethy.core.api.response.PageSupport;
import com.greethy.core.util.ServerRequestUtil;
import com.greethy.user.api.rest.dto.response.UserResponse;
import com.greethy.user.core.domain.exception.NotFoundException;
import com.greethy.user.core.port.in.query.*;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    private final ReactorQueryGateway queryGateway;

    private final ExceptionHandler exceptionHandler;

    /**
     * Retrieves all users from the data source.
     * <p>
     * This method constructs a query to find all users and then streams the query results using a reactive query gateway.
     * It collects the streamed user responses into a list. If the list is not empty, it returns an HTTP 200 OK response
     * along with the list of user responses. If the list is empty, indicating that no users were found, it returns an HTTP
     * 204 No Content response.
     *
     * @return A {@link Mono} representing the server response, which may be an HTTP 200 OK response containing the list
     * of user responses, or an HTTP 204 No Content response if no users were found.
     */
    public Mono<ServerResponse> findAllUser() {
        return Flux.just(new FindAllUserQuery())
                .flatMap(query -> queryGateway.streamingQuery(query, UserResponse.class))
                .collectList()
                .flatMap(usersResponse -> usersResponse.isEmpty()
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok().bodyValue(usersResponse));
    }

    /**
     * Retrieves a paginated list of user data based on the provided offset and limit parameters.
     * <p>
     * This method extracts the values of the "offset" and "limit" query parameters from the provided {@code serverRequest}.
     * It then constructs a query to fetch users with pagination parameters and retrieves the data asynchronously.
     * The method combines the streamed user data and the total count of users into a paginated response.
     * Finally, it returns a server response with the paginated user data in JSON format.
     *
     * @param serverRequest The server request containing the query parameters for pagination.
     * @return A {@code Mono} emitting a {@code ServerResponse} containing the paginated user data in JSON format.
     */
    public Mono<ServerResponse> findAllUserWithPagination(ServerRequest serverRequest) {
        int offset = ServerRequestUtil.getQueryParamIntValue(serverRequest, "offset", "0");
        int limit = ServerRequestUtil.getQueryParamIntValue(serverRequest, "limit", "10");
        return Flux.just(GetAllUserWithPageableQuery.builder()
                        .offset(offset)
                        .limit(limit)
                        .build())
                .flatMap(query -> queryGateway.streamingQuery(query, UserResponse.class))
                .collectList()
                .zipWith(queryGateway.query(new CountAllUserQuery(), Long.class))
                .map(zippedResponse -> new PageSupport<>(zippedResponse.getT1(), offset, limit, zippedResponse.getT2()))
                .flatMap(pageResponse -> pageResponse.content().isEmpty()
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(pageResponse));
    }

    public Mono<ServerResponse> findUserById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("user-id"))
                .map(FindUserByIdQuery::new)
                .flatMap(query ->
                        queryGateway.query(query, UserResponse.class).switchIfEmpty(Mono.error(NotFoundException::new)))
                .flatMap(userResponse -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(userResponse))
                .onErrorResume(exceptionHandler::handlingException);
    }

    /**
     * Retrieves a user by its username or email from the data source.
     * <p>
     * This method extracts the username or email from the query parameter "username-or-email" of the provided {@code serverRequest}.
     * It constructs a query to find a user by the extracted username or email, then executes the query using a reactive query gateway.
     * If no user is found with the specified username or email, it returns an HTTP 404 Not Found response.
     * If an error occurs during the execution, it handles the exception and generates an appropriate response.
     *
     * @param serverRequest The server request containing the query parameter "username-or-email".
     * @return A {@link Mono} representing the server response. If a user with the specified username or email is found, it contains
     * an HTTP 200 OK response with the user details. If no user is found, it contains an HTTP 404 Not Found response.
     */
    public Mono<ServerResponse> findUserByUsernameOrEmail(ServerRequest serverRequest) {
        return Mono.just(serverRequest.queryParam("username-or-email").orElse(""))
                .map(FindUserByUsernameOrEmailQuery::new)
                .flatMap(query ->
                        queryGateway.query(query, UserResponse.class).switchIfEmpty(Mono.error(NotFoundException::new)))
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    /**
     * Checks if a user with the provided email exists in the data source.
     * <p>
     * This method extracts the email from the query parameter "email" of the provided {@code serverRequest}.
     * It constructs a query to check if a user exists with the provided email, then executes the query using a reactive query gateway.
     * The result indicates whether a user with the provided email exists or not.
     * The method returns an HTTP 200 OK response with the result.
     *
     * @param serverRequest The server request containing the query parameter "email".
     * @return A {@link Mono} representing the server response containing an HTTP 200 OK response with the result.
     */
    public Mono<ServerResponse> checkIfUserEmailExists(ServerRequest serverRequest) {
        return Mono.just(serverRequest.queryParam("email").orElse(""))
                .map(CheckIfUserEmailExistsQuery::new)
                .flatMap(query -> queryGateway.query(query, Boolean.class))
                .flatMap(isExisted -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(isExisted));
    }
}
