package com.greethy.personal.service.impl;

import com.greethy.personal.dto.UserRequest;
import com.greethy.personal.dto.UserResponse;
import com.greethy.personal.repository.UserRepository;
import com.greethy.personal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserResponse> save(UserRequest userRequest) {

        return null;
    }
}
