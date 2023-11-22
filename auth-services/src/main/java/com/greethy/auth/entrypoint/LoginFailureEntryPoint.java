package com.greethy.auth.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greethy.auth.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom authentication entry point for handling login failures.
 * Implements {@link AuthenticationEntryPoint}.
 *
 * @author ThanhKien
 */
@Component
@RequiredArgsConstructor
public class LoginFailureEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    /**
     * Commences the authentication entry point, sending an unauthorized response in case of login failure.
     *
     * @param request        the Servlet's HTTP request
     * @param response       the Servlet's HTTP response
     * @param authException  the authentication exception
     * @throws IOException   if an I/O error occurs during the writing of the response
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        var errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized Request !");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getOutputStream()
                .println(mapper.writeValueAsString(errorResponse));
    }

}
