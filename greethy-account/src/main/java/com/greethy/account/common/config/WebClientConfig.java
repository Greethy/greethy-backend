package com.greethy.account.common.config;

import com.greethy.account.common.config.security.keycloak.ReactiveKeycloakOAuth2AccessTokenResponseClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private static final String CLIENT_REGISTRATION_ID = "keycloak";

    @Bean("oauth2WebClientBuilder")
    public WebClient.Builder webClientBuilder(ReactiveClientRegistrationRepository clientRegistrations,
                                       ReactiveOAuth2AuthorizedClientService oAuth2AuthorizedClientService,
                                       ReactiveKeycloakOAuth2AccessTokenResponseClient accessTokenResponseClient) {
        var authorizedClientProvider = ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                .authorizationCode().refreshToken()
                .clientCredentials(clientCredentialsGrantBuilder -> clientCredentialsGrantBuilder.accessTokenResponseClient(accessTokenResponseClient))
                .build();
        var manager = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(clientRegistrations, oAuth2AuthorizedClientService);
        manager.setAuthorizedClientProvider(authorizedClientProvider);
        var oauth2ClientFilter = new ServerOAuth2AuthorizedClientExchangeFilterFunction(manager);
        oauth2ClientFilter.setDefaultClientRegistrationId(CLIENT_REGISTRATION_ID);

        return WebClient.builder()
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                }).filter(oauth2ClientFilter);
    }

    @Bean("defaultWebClientBuilder")
    public WebClient.Builder defaultWebClientBuilder() {
        return WebClient.builder();
    }

}
