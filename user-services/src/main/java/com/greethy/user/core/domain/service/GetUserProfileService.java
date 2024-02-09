package com.greethy.user.core.domain.service;

import com.greethy.annotation.hexagonal.UseCase;
import com.greethy.user.api.rest.request.ProfileRequest;
import com.greethy.user.core.domain.entity.Profile;
import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.port.inbound.CreateUserProfileUseCase;
import com.greethy.user.core.port.outbound.FindUserPort;
import com.greethy.user.core.port.outbound.CreateUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class GetUserProfileService implements CreateUserProfileUseCase {

    private final FindUserPort findUserPort;

    private final CreateUserPort saveUserPort;

    private final ModelMapper mapper;

    @Override
    public Mono<User> createNewProfile(String id, ProfileRequest request) {
        return findUserPort.findById(id)
                .map(user -> {
                    var profile = mapper.map(request, Profile.class);
                    user.setProfile(profile);
                    return user;
                })
                .flatMap(saveUserPort::create)
                .doOnError(throwable -> log.error(throwable.getMessage()));
    }
}
