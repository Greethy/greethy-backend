package com.greethy.auth.service.impl;

import com.greethy.auth.dto.LoginRequest;
import com.greethy.auth.dto.LoginResponse;
import com.greethy.auth.dto.RegisterRequest;
import com.greethy.auth.dto.RegisterResponse;
import com.greethy.auth.entity.Role;
import com.greethy.auth.entity.Token;
import com.greethy.auth.entity.User;
import com.greethy.auth.exception.DuplicateUniqueFieldException;
import com.greethy.auth.exception.InvalidTokenException;
import com.greethy.auth.repository.RoleRepository;
import com.greethy.auth.repository.UserRepository;
import com.greethy.auth.service.AuthService;
import com.greethy.auth.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;

    private final ModelMapper mapper;

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AuthenticationManager manager;

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * Authenticates a user based on the provided login credentials.
     *
     * @param loginRequest The login request containing user credentials.
     * @return A LoginResponse containing authentication details, including access and refresh tokens.
     * @throws AuthenticationException If authentication fails.
     */
    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) throws AuthenticationException {
        String username = obtain(Optional.ofNullable(loginRequest.getUsername()));
        String password = obtain(Optional.ofNullable(loginRequest.getPassword()));

        var authToken = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        Authentication authentication = manager.authenticate(authToken);

        User user = userRepository.findByUsername(username).orElseThrow();
        Token token = Token.builder()
                .accessToken(jwtUtil.generateToken(username))
                .refreshToken(jwtUtil.generateRefreshToken(username))
                .createdAt(LocalDateTime.now())
                .build();

        user.getTokens().add(token);
        userRepository.save(user);

        return LoginResponse.builder()
                .id(user.getId())
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .isAuthenticated(authentication.isAuthenticated())
                .build();
    }

    /**
     * Obtains a trimmed String value from an Optional<String>.If the Optional
     * contains a non-null and non-empty String, the String will be trimmed and returned.
     * If the Optional is empty or contains a null String, an empty String is returned.
     *
     * @param inputString an Optional<String> that may contain a String value
     * @return a trimmed String value if present, or an empty String if not present or null
     */
    private String obtain(Optional<String> inputString){
        return inputString.map(String::trim).orElse("");
    }

    /**
     * Registers a new user based on the provided registration request.
     *
     * @param registerRequest The registration request containing user information.
     * @return A response containing the user ID, access token, and refresh token.
     * @throws DuplicateUniqueFieldException If a user with the given username or email already exists.
     * @throws InvalidTokenException If the provided OTP is invalid.
     */
    @Override
    public RegisterResponse register(RegisterRequest registerRequest)
            throws DuplicateUniqueFieldException, InvalidTokenException {
        verifyOTP(registerRequest.getEmail(), registerRequest.getOtp());
        checkIfUserExists(registerRequest.getUsername(), registerRequest.getEmail());

        User user = mapper.map(registerRequest, User.class);
        String username = user.getUsername();
        Role role = roleRepository.findByName("ROLE_USER");
        Token token = Token.builder()
                .accessToken(jwtUtil.generateToken(username))
                .refreshToken(jwtUtil.generateRefreshToken(username))
                .createdAt(LocalDateTime.now())
                .build();

        user.setTokens(Collections.singletonList(token));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(role));
        user.setEnabled(true);

        return RegisterResponse.builder()
                .id(userRepository.save(user).getId())
                .refreshToken(token.getAccessToken())
                .accessToken(token.getRefreshToken())
                .build();
    }

    /**
     * Checks if a user with the provided username or email already exists in the database.
     *
     * @param username The username to check.
     * @param email    The email to check.
     * @throws DuplicateUniqueFieldException If a user with the given username or email already exists.
     */
    private void checkIfUserExists(String username, String email) {
        if (userRepository.existsByUsernameOrEmail(username, email)) {
            throw new DuplicateUniqueFieldException(HttpStatus.CONFLICT, "Mail or Username already used!");
        }
    }

    /**
     * Verifies the OTP (One-Time Password) for the given username.
     *
     * @param email The email associated with the OTP.
     * @param userOtp The OTP provided by the user for verification.
     * @throws InvalidTokenException If the provided OTP is invalid.
     */
    private void verifyOTP(String email, String userOtp) {
        String otp = redisTemplate.opsForValue().get(email);
        if (Optional.ofNullable(otp).isEmpty() || !userOtp.equals(otp)) {
            throw new InvalidTokenException (HttpStatus.BAD_REQUEST, "invalid OTP !");
        }
        redisTemplate.delete(email);
    }


}
