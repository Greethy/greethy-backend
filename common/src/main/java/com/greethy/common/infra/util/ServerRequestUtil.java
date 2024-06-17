package com.greethy.common.infra.util;

import java.util.Optional;

import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.ServerRequest;

public class ServerRequestUtil {

    /**
     * Retrieves the value of a query parameter from the given {@code ServerRequest}, or returns a default value if the parameter is not present or empty.
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
    public static Integer getQueryParamIntegerValue(ServerRequest serverRequest, String name, String defaultValue) {
        return serverRequest.queryParam(name)
                .filter(StringUtils::hasText)
                .or(() -> Optional.of(defaultValue))
                .map(Integer::valueOf)
                .get();
    }

    public static String getQueryParamStringValue(ServerRequest serverRequest, String name, String defaultValue) {
        return serverRequest.queryParam(name)
                .filter(StringUtils::hasText)
                .or(() -> Optional.of(defaultValue))
                .get();
    }
}
