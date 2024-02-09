package com.greethy.user.core.port.inbound;

import com.greethy.user.api.rest.request.ProfileRequest;
import com.greethy.user.core.domain.entity.User;
import reactor.core.publisher.Mono;

public interface CreateUserProfileUseCase {

    Mono<User> createNewProfile(String id, ProfileRequest request);

}
