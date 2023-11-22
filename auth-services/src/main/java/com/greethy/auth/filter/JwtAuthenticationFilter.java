package com.greethy.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greethy.auth.dto.ErrorResponse;
import com.greethy.auth.exception.InvalidTokenException;
import com.greethy.auth.service.impl.UserServiceImpl;
import com.greethy.auth.utility.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final ObjectMapper mapper;

    private final UserServiceImpl userService;

    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        Optional<String> authHeader = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION));
        if (authHeader.filter(header -> header.startsWith(TOKEN_PREFIX)).isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = authHeader.map(header -> header.substring(TOKEN_PREFIX.length())).orElse("");
        try {
            String username = jwtUtil.extractUsername(jwt);
            if(Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).isEmpty()){
               var userDetails = userService.loadUserByUsername(username);
               var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (InvalidTokenException ex) {
            var errorResponse = new ErrorResponse(ex.getStatus(), ex.getMessage());
            response.setStatus(ex.getStatus().value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(mapper.writeValueAsString(errorResponse));
            return; // Stop the filter chain and returns error response.
        }
        filterChain.doFilter(request, response);
    }
}
