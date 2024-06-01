package com.greethy.usercommand.domain.service;

import com.greethy.usercommon.dto.request.command.UpdateUserProfileCommand;
import com.greethy.usercommon.dto.response.UserResponse;
import com.greethy.usercommon.dto.request.command.RegisterUserCommand;

import reactor.core.publisher.Mono;

public interface UserCommandService {

    Mono<UserResponse> registerUser(RegisterUserCommand command);

    Mono<UserResponse> updateUserProfile(String userId, UpdateUserProfileCommand command);

}
