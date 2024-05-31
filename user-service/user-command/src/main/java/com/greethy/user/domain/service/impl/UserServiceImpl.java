package com.greethy.user.domain.service.impl;

import com.greethy.user.domain.port.NetworkingPort;
import com.greethy.user.domain.port.RolePort;
import com.greethy.user.domain.port.UserPort;
import com.greethy.user.domain.service.UserService;
import com.greethy.user.common.dto.request.command.RegisterUserCommand;
import com.greethy.user.common.dto.request.command.UpdateUserProfileCommand;
import com.greethy.user.common.dto.response.UserResponse;
import com.greethy.user.common.entity.Networking;
import com.greethy.user.common.entity.User;
import com.greethy.user.common.entity.value.PersonalDetail;
import com.greethy.user.common.exception.DuplicateUniqueFieldException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Collections;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper mapper;

    private final RolePort mongoRolePort;

    private final UserPort mongoUserPort;

    private final NetworkingPort mongoNetworkingPort;

    public UserServiceImpl(ModelMapper mapper,
                           RolePort mongoRolePort,
                           @Qualifier("mongoUserAdapter") UserPort mongoUserPort,
                           NetworkingPort mongoNetworkingPort) {
        this.mapper = mapper;
        this.mongoRolePort = mongoRolePort;
        this.mongoUserPort = mongoUserPort;
        this.mongoNetworkingPort = mongoNetworkingPort;
    }

    @Override
    public Mono<UserResponse> registerUser(RegisterUserCommand createUserCommand) {
        return mongoUserPort.existsByUsernameOrEmail(createUserCommand.getUsername(), createUserCommand.getEmail())
                .filter(isExisted -> !isExisted)
                .switchIfEmpty(Mono.error(new DuplicateUniqueFieldException("Username or email already in used !")))
                .then(Mono.just(createUserCommand))
                .map(command -> mapper.map(command, User.class))
                .zipWith(mongoRolePort.getRoleByDefaultIsTrue())
                .doOnNext(tuple -> {
                    User user = tuple.getT1();
                    user.setNetworking(new Networking(UUID.randomUUID().toString()));
                    user.setRoles(Collections.singletonList(tuple.getT2()));
                })
                .map(Tuple2::getT1)
                .flatMap(user -> mongoUserPort.save(user)
                        .map(User::getNetworking)
                        .flatMap(mongoNetworkingPort::save)
                        .thenReturn(user)
                ).doOnNext(user -> log.info("User {} saved to MongoDB", user.getId()))
                .map(user -> mapper.map(user, UserResponse.class));
    }

    @Override
    public Mono<UserResponse> updateUserProfile(UpdateUserProfileCommand command) {
        return mongoUserPort.findById(command.getUserId())
                .doOnNext(user -> {
                    var personalDetail = mapper.map(command.getPersonalDetail(), PersonalDetail.class);
                    user.setAvatarUrl(command.getAvatarUrl());
                    user.setBannerUrl(command.getBannerUrl());
                    user.setBio(command.getBio());
                    user.setPersonalDetail(personalDetail);
                })
                .flatMap(mongoUserPort::save)
                .doOnNext(user -> log.info("User {} has been updated, info: {}", user.getId(), user))
                .map(user -> mapper.map(user, UserResponse.class));
    }

}
