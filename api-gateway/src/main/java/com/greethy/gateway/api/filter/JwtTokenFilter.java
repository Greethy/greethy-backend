package com.greethy.gateway.api.filter;

import com.greethy.gateway.infra.config.security.constant.SecurityConstants;
import com.greethy.gateway.infra.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter implements WebFilter {

    private final JwtTokenProvider tokenProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = resolveToken(exchange.getRequest());
        if(StringUtils.hasText(token)) {
            return Mono.fromCallable(() -> tokenProvider.getAuthentication(token))
                    .subscribeOn(Schedulers.boundedElastic())
                    .flatMap(authentication -> chain.filter(exchange)
                            .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
                    );
        }
        return chain.filter(exchange);
    }

    private String resolveToken(ServerHttpRequest request) {
        var requestHeader = request.getHeaders();
        return Optional.ofNullable(requestHeader.getFirst(HttpHeaders.AUTHORIZATION))
                .filter(StringUtils::hasText)
                .filter(bearToken -> bearToken.startsWith(SecurityConstants.REQUEST_HEADER_TOKEN_PREFIX))
                .map(bearerToken -> bearerToken.substring(7))
                .orElse("");
    }
}

