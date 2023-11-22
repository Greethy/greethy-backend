package com.greethy.auth.utility;

import com.greethy.auth.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    @Value("${spring.application.jwt.issuer}")
    private String issuer;

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
     * @throws InvalidTokenException if the token is invalid, expired, or if an exception occurs
     *                               during the parsing process
     */
    public String extractUsername(String token) throws InvalidTokenException {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from a JWT (JSON Web Token) by parsing the token and applying
     * a provided {@link Function} to resolve the desired claim from the token's {@link Claims}.
     *
     * @param <T>             the type of the claim to be extracted
     * @param token           the JWT from which to extract the claim
     * @param claimsResolver  a {@link Function} that takes a {@link Claims} object and returns
     *                        the desired claim of type {@code T}
     * @return the extracted claim of type {@code T}
     * @throws InvalidTokenException if the token is invalid, expired, or if an exception occurs
     *                               during the parsing process
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = Jwts.parserBuilder()
                    .setSigningKey(signinKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claimsResolver.apply(claims);
        } catch (Exception ex) {
            throw new InvalidTokenException(HttpStatus.UNAUTHORIZED, "This token has expired or invalid !");
        }
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
                .setClaims(extraClaims)     //setClaims must be set first to avoid overwriting Issuer or Subject.
                .setIssuer(issuer)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(signinKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
