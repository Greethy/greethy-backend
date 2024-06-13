package com.greethy.usercommand.domain.service;

import com.greethy.usercommon.dto.request.command.UpdateUserProfileCommand;
import com.greethy.usercommon.dto.response.UserResponse;
import com.greethy.usercommon.dto.request.command.UserRegistrationCommand;

import reactor.core.publisher.Mono;

public interface UserCommandService {

    Mono<UserResponse> registerUser(UserRegistrationCommand command);

    Mono<UserResponse> updateUserProfile(String userId, UpdateUserProfileCommand command);

}
