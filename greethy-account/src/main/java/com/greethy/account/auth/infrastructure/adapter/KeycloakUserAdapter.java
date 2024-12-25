package com.greethy.account.auth.infrastructure.adapter;

import com.greethy.account.common.exception.KeycloakClientException;
import com.greethy.account.common.config.security.keycloak.KeycloakProperties;
import com.greethy.account.auth.domain.port.UserDrivenPort;
import com.greethy.account.auth.infrastructure.model.KeycloakErrorResponse;
import com.greethy.account.auth.infrastructure.model.KeycloakUser;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;


@Component("userKeycloakAdapter")
public class KeycloakUserAdapter implements UserDrivenPort {

    private final WebClient webClient;
    private final EmailValidator emailValidator;
    private final KeycloakProperties keycloakProperties;
    private static final String RESOURCE_PATH = "/admin/realms/{realm}/users";

    public KeycloakUserAdapter(EmailValidator emailValidator,
                               KeycloakProperties keycloakProperties,
                               @Qualifier("oauth2WebClientBuilder") WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(keycloakProperties.getBaseUrl())
                .build();
        this.emailValidator = emailValidator;
        this.keycloakProperties = keycloakProperties;

    }

    @Override
    public Mono<String> save(KeycloakUser user) {
        return this.webClient.post()
                .uri(RESOURCE_PATH, keycloakProperties.getRealm())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> clientResponse.bodyToMono(KeycloakErrorResponse.class)
                                .map(KeycloakErrorResponse::getErrorMessage)
                                .flatMap(message -> Mono.error(KeycloakClientException.of(message, clientResponse.statusCode())))
                ).toBodilessEntity()
                .map(clientResponse -> {
                    String uriPath = Objects.requireNonNull(clientResponse.getHeaders().getLocation()).getPath();
                    return List.of(uriPath.split("/")).getLast();   // Return User ID.
                });
    }

    @Override
    public Mono<KeycloakUser> findById(String s) {
        return null;
    }

    @Override
    public Flux<KeycloakUser> findAllBy(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public Mono<Boolean> exists(@NonNull final String usernameOrEmail) {
        return count(usernameOrEmail).map(count -> count > 0);
    }

    @Override
    public Mono<Integer> count(@NonNull final String usernameOrEmail) {
        var queryParameters = new LinkedMultiValueMap<String, String>();
        String queryParam = emailValidator.isValid(usernameOrEmail) ? "email" : "username";
        queryParameters.add(queryParam, usernameOrEmail);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(RESOURCE_PATH + "/count")
                        .queryParams(queryParameters)
                        .build(keycloakProperties.getRealm()))
                .retrieve()
                .bodyToMono(Integer.class);
    }

}
