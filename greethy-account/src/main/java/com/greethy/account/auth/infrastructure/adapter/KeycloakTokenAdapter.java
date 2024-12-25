package com.greethy.account.auth.infrastructure.adapter;

import com.greethy.account.auth.domain.port.TokenDrivenPort;
import com.greethy.account.auth.infrastructure.model.KeycloakToken;
import com.greethy.account.common.config.security.keycloak.KeycloakProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Component
public class KeycloakTokenAdapter implements TokenDrivenPort {

    private final WebClient webClient;
    private final KeycloakProperties keycloakProperties;
    private static final int MAX_RETRIES = 3;

    public KeycloakTokenAdapter(@Qualifier("defaultWebClientBuilder") WebClient.Builder webClientBuilder,
                                KeycloakProperties keycloakProperties) {
        this.webClient = webClientBuilder.baseUrl(keycloakProperties.getBaseUrl()).build();
        this.keycloakProperties = keycloakProperties;
    }

    public Mono<KeycloakToken> getToken(String usernameOrEmail, String password) {
        return webClient.post()
                .uri("/realms/{realm}/protocol/openid-connect/token", keycloakProperties.getRealm())
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED))
                .body(authorizeBody(usernameOrEmail, password))
                .retrieve()
                .bodyToMono(KeycloakToken.class)
                .retryWhen(Retry.fixedDelay(MAX_RETRIES, Duration.ofSeconds(2))
                        .filter(this::is4xxClientError)
                        .doAfterRetry(retrySignal -> {
                            log.info("Attempting {}/{} retries.", retrySignal.totalRetries(), MAX_RETRIES);
                        })
                );
    }

    private boolean is4xxClientError(Throwable throwable) {
        return ( throwable instanceof WebClientResponseException )
                && ( (WebClientResponseException) throwable ).getStatusCode().is4xxClientError();
    }

    private BodyInserters.FormInserter<String> authorizeBody(String usernameOrEmail, String password) {
        var formParameters = new LinkedMultiValueMap<String, String>();
        formParameters.add(OAuth2ParameterNames.GRANT_TYPE, keycloakProperties.getGrantType());
        formParameters.add(OAuth2ParameterNames.CLIENT_ID, keycloakProperties.getClientId());
        formParameters.add(OAuth2ParameterNames.CLIENT_SECRET, keycloakProperties.getClientSecret());
        formParameters.add(OAuth2ParameterNames.SCOPE, keycloakProperties.getScope());
        formParameters.add(OAuth2ParameterNames.USERNAME, usernameOrEmail);
        formParameters.add(OAuth2ParameterNames.PASSWORD, password);
        return BodyInserters.fromFormData(formParameters);
    }

}
