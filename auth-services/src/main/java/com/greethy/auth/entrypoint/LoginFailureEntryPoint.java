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

@Component
@RequiredArgsConstructor
public class LoginFailureEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        var errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Wrong Username or Password, please try again!");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getOutputStream().println(mapper.writeValueAsString(errorResponse));
    }

}
