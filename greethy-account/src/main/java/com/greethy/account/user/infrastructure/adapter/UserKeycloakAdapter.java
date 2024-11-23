package com.greethy.account.user.infrastructure.adapter;

import com.greethy.account.config.security.keycloak.KeycloakProperties;
import com.greethy.account.user.domain.entity.User;
import com.greethy.account.user.domain.port.UserDrivenPort;
import com.greethy.account.user.infrastructure.model.KeycloakErrorResponse;
import com.greethy.account.user.infrastructure.model.KeycloakUser;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component("userKeycloakAdapter")
public class UserKeycloakAdapter implements UserDrivenPort {

    private final WebClient webClient;
    private final EmailValidator emailValidator;
    private final KeycloakProperties keycloakProperties;

    public UserKeycloakAdapter(EmailValidator emailValidator,
                               KeycloakProperties keycloakProperties,
                               WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(keycloakProperties.getBaseUrl())
                .build();
        this.emailValidator = emailValidator;
        this.keycloakProperties = keycloakProperties;

    }

    @Override
    public Mono<Void> save(KeycloakUser user) {
        return this.webClient.post()
                .uri(keycloakProperties.getResourcePath(), keycloakProperties.getRealm())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> response
                        .bodyToMono(KeycloakErrorResponse.class)
                        .flatMap(errorResponse -> Mono.error(new Exception(errorResponse.getErrorMessage())))
                ).bodyToMono(Void.class);
    }

    @Override
    public Mono<KeycloakUser> findById(String s) {
        return null;
    }

    @Override
    public Flux<KeycloakUser> findAllBy(Integer pageNumber, Integer pageSize) {
        return null;
    }

    /**
     *
     */
    @Override
    public Flux<KeycloakUser> getUsers() {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/" + keycloakProperties.getRealm() + "/users")
                        .queryParam("exact", false)
                        .build())
                .retrieve().bodyToFlux(KeycloakUser.class);
    }

    @Override
    public Mono<Boolean> exists(@NonNull String usernameOrEmail) {
        var queryParameters = new LinkedMultiValueMap<String, String>();
        queryParameters.add("exact", Boolean.TRUE.toString());
        if (emailValidator.isValid(usernameOrEmail)) {
            queryParameters.add("email", usernameOrEmail);
        } else {
            queryParameters.add("username", usernameOrEmail);
        }
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/" + keycloakProperties.getRealm() + "/users")
                        .queryParams(queryParameters)
                        .build())
                .retrieve()
                .bodyToFlux(KeycloakUser.class)
                .collectList()
                .flatMap(keycloakUsers -> keycloakUsers.isEmpty() ? Mono.just(Boolean.FALSE) : Mono.just(Boolean.TRUE));

    }

    public Mono<KeycloakUser> userAccessToken() {
        String urlPath = "/admin/realms/" + keycloakProperties.getRealm() + "/protocol/openid-connect/token";
        return null;
    }

}
