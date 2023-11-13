package com.greethy.auth.service.impl;

import com.greethy.auth.dto.LoginRequest;
import com.greethy.auth.dto.RegisterRequest;
import com.greethy.auth.dto.RegisterResponse;
import com.greethy.auth.entity.Role;
import com.greethy.auth.entity.Token;
import com.greethy.auth.entity.User;
import com.greethy.auth.exception.DuplicateUniqueFieldException;
import com.greethy.auth.repository.RoleRepository;
import com.greethy.auth.repository.UserRepository;
import com.greethy.auth.service.AuthService;
import com.greethy.auth.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @Override
    public void authenticate(LoginRequest loginRequest) {
        String username = obtain(Optional.ofNullable(loginRequest.getUsername()));
        String password = obtain(Optional.ofNullable(loginRequest.getPassword()));
        var authToken = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        System.out.println(username + " " + password);
        System.out.println(authToken.isAuthenticated());
        Authentication authentication = manager.authenticate(authToken);
        System.out.println(authentication.isAuthenticated());
    }

    private String obtain(Optional<String> inputString){
        return inputString.map(String::trim).orElse("");
    }

    /**
     * Registers a new user based on the provided registration request.
     *
     * @param registerRequest The registration request containing user details.
     * @return The response containing user registration information.
     */
    @Override
    public RegisterResponse register(RegisterRequest registerRequest){
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
            throw new DuplicateUniqueFieldException(HttpStatus.CONFLICT, "Email or Username already used!");
        }
    }


}
