package com.greethy.usercommand.domain.service;

import com.greethy.usercommon.dto.request.command.UserRegistrationCommand;
import com.greethy.usercommon.dto.request.command.UserLoginCommand;
import com.greethy.usercommon.dto.response.AuthResponse;
import reactor.core.publisher.Mono;

public interface AuthCommandService {

    Mono<AuthResponse> authenticate(UserLoginCommand command);

    Mono<AuthResponse> registerGreethyUser(UserRegistrationCommand command);

}
