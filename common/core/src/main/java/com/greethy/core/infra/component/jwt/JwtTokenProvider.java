package com.greethy.core.infra.component.jwt;

import com.greethy.core.infra.config.properties.JwtProperties;
import com.greethy.core.infra.util.DataUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private SecretKey secretKey;

    private static final String AUTHORITIES_KEY = "roles";

    @PostConstruct
    void init() {
        String secret = Base64.getEncoder()
                .encodeToString(this.jwtProperties.getSecretKey().getBytes());
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Authentication authentication) {
        return createToken(authentication.getName(), authentication.getAuthorities());
    }

    public String createToken(String username) {
        var defaultAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_REGULAR"));
        return createToken(username, defaultAuthorities);
    }

    public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
        ClaimsBuilder claimsBuilder = Jwts.claims().subject(username);
        if (!DataUtil.isNullOrEmpty(authorities)) {
            claimsBuilder.add(
                    AUTHORITIES_KEY,
                    authorities.stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(", ")));
        }
        Claims claims = claimsBuilder.build();
        return Jwts.builder()
                .claims(claims)
                .issuer(jwtProperties.getIss())
                .issuedAt(new Date())
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        Object authoritiesClaims = claims.get(AUTHORITIES_KEY);
        Collection<? extends GrantedAuthority> authorities = Objects.isNull(authoritiesClaims)
                ? AuthorityUtils.NO_AUTHORITIES
                : AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaims.toString());
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(this.secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            log.error("Invalid JWT token: {}", ex.getMessage());
            log.trace("Invalid JWT token, trace: ", ex);
            return false;
        }
    }
}
