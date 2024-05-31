package com.greethy.user.domain.service;

import com.greethy.user.common.dto.request.command.UpdateUserProfileCommand;
import com.greethy.user.common.dto.response.UserResponse;
import com.greethy.user.common.dto.request.command.RegisterUserCommand;

import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserResponse> registerUser(RegisterUserCommand command);

    Mono<UserResponse> updateUserProfile(UpdateUserProfileCommand command);

}
