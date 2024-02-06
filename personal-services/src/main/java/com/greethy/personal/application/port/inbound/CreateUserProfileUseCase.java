package com.greethy.personal.application.port.inbound;

import com.greethy.personal.presentation.rest.request.ProfileRequest;
import com.greethy.personal.application.domain.entity.User;
import reactor.core.publisher.Mono;

public interface CreateUserProfileUseCase {

    Mono<User> createNewProfile(String id, ProfileRequest request);

}
