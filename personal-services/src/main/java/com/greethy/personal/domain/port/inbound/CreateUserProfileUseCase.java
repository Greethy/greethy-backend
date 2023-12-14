package com.greethy.personal.domain.port.inbound;

import com.greethy.personal.application.rest.request.ProfileRequest;
import com.greethy.personal.domain.entity.User;
import reactor.core.publisher.Mono;

public interface CreateUserProfileUseCase {

    Mono<User> createNewProfile(String id, ProfileRequest request);

}
