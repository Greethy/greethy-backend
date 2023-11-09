package com.greethy.auth.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A utility class provide some operation for working with JSON Web Tokens (JWT).
 *
 * @author ThanhKien
 */
@Component
public class JwtUtil {

    @Value("${spring.application.jwt.secret}")
    private String secretKey;

    @Value("${spring.application.jwt.expired}")
    private long expiredTime;

    @Value("${spring.application.jwt.refresh-expired}")
    private long refreshExpiredTime;

    /**
     * Extracts the username from a JSON Web Token (JWT).
     *
     * @param token The JWT from which the username needs to be extracted.
     * @return The username extracted from the JWT, or null if it cannot be extracted.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts information from a JSON Web Token (JWT) using a Claims resolver function.
     *
     * @param token          The JWT (JS Web Token) from which information needs to be extracted.
     * @param claimsResolver A functional interface that resolves Claims
     *                       and returns the extracted value from the Claims.
     * @param <T>            The data type of the extracted value.
     * @return The extracted value from the JWT using the provided Claims resolver.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(signinKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    /**
     * Creates a new SecretKey instance for use with HMAC-SHA algorithms based on
     * the key byte array which is decoded from secretKey by Base64 Decoder
     *
     * @return The signing key as a Key object.
     */
    private Key signinKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generates an access token with the specified subject.
     *
     * @param subject The subject for the token.
     * @return The generated access token.
     */
    public String generateToken(String subject) {
        return generateToken(subject, new HashMap<>());
    }

    /**
     * Generates an access token with the specified extra claims and subject.
     * @param subject     The subject for the token.
     * @param extraClaims Additional claims to include in the token.
     * @return The generated access token.
     */
    public String generateToken(String subject,
                                Map<String, Object> extraClaims) {
        return createToken(subject, extraClaims, expiredTime);
    }

    /**
     * Generates a refresh token with the specified subject.
     *
     * @param subject The subject for the refresh token.
     * @return The generated refresh token.
     */
    public String generateRefreshToken(String subject) {
        return generateRefreshToken( subject, new HashMap<>());
    }

    /**
     * Generates a refresh token with the specified extra claims and subject.
     * @param subject     The subject for the token.
     * @param extraClaims Additional claims to include in the token.
     * @return The generated refresh token.
     */
    public String generateRefreshToken(String subject, Map<String, Object> extraClaims) {
        return createToken(subject, extraClaims, refreshExpiredTime);
    }

    /**
     * Create new JWT with the provided extra claims, subject, and expiration time.
     *
     * @param extraClaims Additional claims to include in the token.
     * @param subject     The subject for the token.
     * @param expiredTime The expiration time for the token in milliseconds.
     * @return The generated JWT token.
     */
    private String createToken(String subject,
                               Map<String, Object> extraClaims,
                               long expiredTime) {
        return Jwts.builder()
                .setSubject(subject)
                .setClaims(extraClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(signinKey(), SignatureAlgorithm.RS256)
                .compact();
    }

}
