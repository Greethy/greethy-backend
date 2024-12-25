package com.greethy.account.common.config.security.keycloak;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2ClientCredentialsGrantRequest;
import org.springframework.security.oauth2.client.endpoint.ReactiveOAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Set;

import static org.springframework.security.oauth2.core.web.reactive.function.OAuth2BodyExtractors.oauth2AccessTokenResponse;

@Component
public class ReactiveKeycloakOAuth2AccessTokenResponseClient implements ReactiveOAuth2AccessTokenResponseClient<OAuth2ClientCredentialsGrantRequest> {

    private final WebClient webClient;

    public ReactiveKeycloakOAuth2AccessTokenResponseClient(@Qualifier("defaultWebClientBuilder") WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<OAuth2AccessTokenResponse> getTokenResponse(OAuth2ClientCredentialsGrantRequest authorizationGrantRequest) {
        return Mono.defer(() -> {
            ClientRegistration clientRegistration = authorizationGrantRequest.getClientRegistration();
            String tokenUri = clientRegistration.getProviderDetails().getTokenUri();
            return webClient.post()
                    .uri(tokenUri)
                    .accept(MediaType.APPLICATION_JSON)
                    .attribute(OAuth2AuthorizationCodeGrantRequest.class.getName(), authorizationGrantRequest)
                    .headers(httpHeaders -> {
                        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                        if (ClientAuthenticationMethod.CLIENT_SECRET_BASIC.equals(clientRegistration.getClientAuthenticationMethod())) {
                            httpHeaders.setBasicAuth(clientRegistration.getClientId(), clientRegistration.getClientSecret());
                        }
                    })
                    .body(body(clientRegistration))
                    .exchangeToMono(clientResponse -> {
                        HttpStatusCode status = clientResponse.statusCode();
                        if (!status.is2xxSuccessful()) {
                            return clientResponse.bodyToFlux(DataBuffer.class)
                                    .map(DataBufferUtils::release)
                                    .then(Mono.error(WebClientResponseException.create(clientResponse.statusCode().value(), "Cannot get token, expected 2xx HTTP Status code", null, null, null)));
                        }
                        return clientResponse.body(oauth2AccessTokenResponse());
                    }).map(response -> {
                        if (response.getAccessToken().getScopes().isEmpty()) {
                            response = OAuth2AccessTokenResponse.withResponse(response)
                                    .scopes(authorizationGrantRequest.getClientRegistration().getScopes())
                                    .build();
                        }
                        return response;
                    });
        });
    }

    private BodyInserters.FormInserter<String> body( ClientRegistration clientRegistration) {
        var formParameters = new LinkedMultiValueMap<String, String>();

        formParameters.add(OAuth2ParameterNames.GRANT_TYPE, clientRegistration.getAuthorizationGrantType().getValue());
        Set<String> scopes = clientRegistration.getScopes();
        if (!CollectionUtils.isEmpty(scopes)) {
            String scope = StringUtils.collectionToDelimitedString(scopes, " ");
            formParameters.add(OAuth2ParameterNames.SCOPE, scope);
        }
        formParameters.add(OAuth2ParameterNames.CLIENT_ID, clientRegistration.getClientId());
        formParameters.add(OAuth2ParameterNames.CLIENT_SECRET, clientRegistration.getClientSecret());
        return BodyInserters.fromFormData(formParameters);
    }

}
