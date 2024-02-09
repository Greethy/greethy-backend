package com.greethy.personal.core.port.inbound;

import com.greethy.personal.api.rest.request.ProfileRequest;
import com.greethy.personal.core.domain.entity.User;
import reactor.core.publisher.Mono;

public interface CreateUserProfileUseCase {

    Mono<User> createNewProfile(String id, ProfileRequest request);

}
