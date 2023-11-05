package com.greethy.auth.filter;

import com.greethy.auth.utility.JwtUtil;
import com.mongodb.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 *
 *
 * @author ThanhKien
 * */
@RequiredArgsConstructor
public class JwtVerifyFilter extends OncePerRequestFilter {


    private final RedisTemplate<String, Object> redisTemplate;

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);
        String username = jwtUtil.extractUsername(jwt);
    }
}

