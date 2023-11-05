package com.greethy.gateway.filter;

import com.greethy.gateway.dto.AuthResponse;
import com.greethy.gateway.exception.InvalidAuthorizationException;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 *
 *
 * @author ThanhKien
 * */
@Component
public class AuthenticationPreFilter extends AbstractGatewayFilterFactory<AuthenticationPreFilter.Config> {

    private final WebClient.Builder webClientBuilder;

    public AuthenticationPreFilter(WebClient.Builder webClientBuilder){
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            var headers = exchange.getRequest().getHeaders();
            if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new InvalidAuthorizationException("Missing authorization information");
            }
            Optional<String> bearerToken = Optional.ofNullable(headers.getFirst(HttpHeaders.AUTHORIZATION));
            assert bearerToken.isPresent();
            String[] tokenParts = bearerToken.get().split("\\s+");
            if (!tokenParts[0].equals("Bearer") || tokenParts.length != 2){
                throw new InvalidAuthorizationException("Incorrect authorization structure");
            }
            return webClientBuilder.build()
                    .get()
                    .uri("lb://auth-services/api/v1/validateToken")
                    .header(HttpHeaders.AUTHORIZATION, tokenParts[1])
                    .retrieve()
                    .bodyToMono(AuthResponse.class)
                    .map(response -> exchange)
                    .flatMap(chain::filter);
        };
    }

    /**
     * This class contain the configuration properties
     * of the custom filter (AuthenticationPreFilter)
     * */
    @NoArgsConstructor
    public static class Config{

    }

}
