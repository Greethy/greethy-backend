package com.greethy.common.infra.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greethy.common.api.response.ErrorResponse;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ResponseUtil {

    public static Mono<Void> formatResponse(ServerHttpResponse response, String message) {

        Integer status = Objects.requireNonNull(response.getStatusCode()).value();
        ErrorResponse errorResponse = new ErrorResponse(status, message);
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder json = new StringBuilder();
        response.getHeaders()
                .add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        try {
            json.append(mapper.writeValueAsString(errorResponse));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String responseBody = json.toString();
        var bytes = responseBody.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(buffer));

    }

}
