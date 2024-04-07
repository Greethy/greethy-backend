package com.greethy.user.api.rest.controller.user;

import com.greethy.core.api.response.PageSupport;
import com.greethy.user.api.rest.dto.response.UserResponse;
import com.greethy.user.core.domain.exception.NotFoundException;
import com.greethy.user.core.port.in.query.*;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
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

    private final ReactorQueryGateway reactiveQueryGateway;

    private final UserExceptionHandler exceptionHandler;

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
                .flatMap(query -> reactiveQueryGateway.streamingQuery(query, UserResponse.class))
                .collectList()
                .flatMap(usersResponse -> usersResponse.isEmpty()
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok().bodyValue(usersResponse));
    }

    /**
     * Retrieves users from the data source with pagination based on the provided query parameters.
     * <p>
     * This method parses the pagination parameters (page and size) from the request using {@link #getQueryParamValue(ServerRequest, String, String)}.
     * It constructs a query to retrieve users with pagination information, then streams the query results using a reactive query gateway.
     * The streamed user responses are collected into a list, and the total count of users is queried separately.
     * The method then combines the list of user responses and the total count into a {@link PageSupport} object representing the paginated response.
     * If the content of the page is empty, indicating no users were found, it returns an HTTP 204 No Content response.
     * Otherwise, it returns an HTTP 200 OK response along with the paginated user responses.
     *
     * @param serverRequest The server request containing pagination parameters.
     * @return A {@link Mono} representing the server response. If users are found, it contains an HTTP 200 OK response
     * with the paginated list of user responses. If no users are found, it contains an HTTP 204 No Content response.
     */
    public Mono<ServerResponse> findAllUserWithPagination(ServerRequest serverRequest) {
        int page = getQueryParamValue(serverRequest, "page", "0");
        int size = getQueryParamValue(serverRequest, "size", "10");
        return Flux.just(GetAllUserWithPageableQuery.builder().page(page).size(size).build())
                .flatMap(query -> reactiveQueryGateway.streamingQuery(query, UserResponse.class))
                .collectList()
                .zipWith(reactiveQueryGateway.query(new CountAllUserQuery(), Long.class))
                .map(zippedResponse -> new PageSupport<>(zippedResponse.getT1(), page, size, zippedResponse.getT2()))
                .flatMap(pageResponse -> pageResponse.content().isEmpty()
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(pageResponse));
    }

    /**
     * Retrieves the value of a query parameter from the given server request, or returns a default value if the parameter is not present or empty.
     * <p>
     * This method extracts the value of the specified query parameter named {@code name} from the provided {@code serverRequest}.
     * If the parameter is present and not empty, its value is parsed into an integer.
     * If the parameter is not present or empty, it falls back to the provided {@code defaultValue}.
     *
     * @param serverRequest The server request from which to extract the query parameter value.
     * @param name          The name of the query parameter to retrieve.
     * @param defaultValue  The default value to return if the query parameter is not present or empty.
     * @return The integer value of the query parameter if present and not empty, or the default value otherwise.
     */
    private int getQueryParamValue(ServerRequest serverRequest, String name, String defaultValue) {
        return serverRequest.queryParam(name)
                .filter(StringUtils::hasText)
                .or(() -> Optional.of(defaultValue))
                .map(Integer::valueOf).get();
    }

    /**
     * Retrieves a user by its ID from the data source.
     * <p>
     * This method extracts the user ID from the path variable of the provided {@code serverRequest}.
     * It constructs a query to find a user by the extracted ID, then executes the query using a reactive query gateway.
     * If no user is found with the specified ID, it returns an HTTP 404 Not Found response.
     * If an error occurs during the execution, it handles the exception and generates an appropriate response.
     *
     * @param serverRequest The server request containing the path variable with the user ID.
     * @return A {@link Mono} representing the server response. If a user with the specified ID is found, it contains
     *         an HTTP 200 OK response with the user details. If no user is found, it contains an HTTP 404 Not Found response.
     */
    public Mono<ServerResponse> findUserById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("user-id"))
                .map(userId -> FindUserByIdQuery.builder().userId(userId).build())
                .flatMap(query -> reactiveQueryGateway.query(query, UserResponse.class)
                        .switchIfEmpty(Mono.error(NotFoundException::new))
                ).flatMap(userResponse -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(userResponse))
                .onErrorResume(exceptionHandler::handlingQueryException);
    }

    /**
     * Retrieves a user by its username or email from the data source.
     *<p>
     * This method extracts the username or email from the query parameter "username-or-email" of the provided {@code serverRequest}.
     * It constructs a query to find a user by the extracted username or email, then executes the query using a reactive query gateway.
     * If no user is found with the specified username or email, it returns an HTTP 404 Not Found response.
     * If an error occurs during the execution, it handles the exception and generates an appropriate response.
     *
     * @param serverRequest The server request containing the query parameter "username-or-email".
     * @return A {@link Mono} representing the server response. If a user with the specified username or email is found, it contains
     *         an HTTP 200 OK response with the user details. If no user is found, it contains an HTTP 404 Not Found response.
     */
    public Mono<ServerResponse> findUserByUsernameOrEmail(ServerRequest serverRequest) {
        return Mono.just(serverRequest.queryParam("username-or-email").orElse(""))
                .map(FindUserByUsernameOrEmailQuery::new)
                .flatMap(query -> reactiveQueryGateway.query(query, UserResponse.class)
                        .switchIfEmpty(Mono.error(NotFoundException::new))
                ).flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingQueryException);
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
                .flatMap(query -> reactiveQueryGateway.query(query, Boolean.class))
                .flatMap(isExisted -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(isExisted));
    }

}
