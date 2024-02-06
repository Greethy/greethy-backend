package com.greethy.personal.application.domain.service;

import com.greethy.annotation.hexagonal.UseCase;
import com.greethy.personal.presentation.rest.request.ProfileRequest;
import com.greethy.personal.application.domain.entity.Profile;
import com.greethy.personal.application.domain.entity.User;
import com.greethy.personal.application.port.inbound.CreateUserProfileUseCase;
import com.greethy.personal.application.port.outbound.FindUserPort;
import com.greethy.personal.application.port.outbound.CreateUserPort;
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
