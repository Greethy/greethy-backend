package com.greethy.usercommand.domain.service.impl;

import com.greethy.common.infra.component.i18n.Translator;
import com.greethy.usercommand.domain.port.NetworkingPort;
import com.greethy.usercommand.domain.port.RolePort;
import com.greethy.usercommand.domain.port.UserPort;
import com.greethy.usercommand.domain.service.UserCommandService;
import com.greethy.usercommon.constant.Constants;
import com.greethy.usercommon.dto.request.command.UserRegistrationCommand;
import com.greethy.usercommon.dto.request.command.UpdateUserProfileCommand;
import com.greethy.usercommon.dto.response.UserResponse;
import com.greethy.usercommon.entity.value.PersonalDetail;
import com.greethy.usercommon.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final ModelMapper mapper;

    private final Translator translator;

    private final RolePort mongoRolePort;

    private final UserPort mongoUserPort;

    private final NetworkingPort mongoNetworkingPort;

    public UserCommandServiceImpl(ModelMapper mapper,
                                  Translator translator,
                                  RolePort mongoRolePort,
                                  @Qualifier("mongoUserAdapter") UserPort mongoUserPort,
                                  NetworkingPort mongoNetworkingPort) {
        this.mapper = mapper;
        this.translator = translator;
        this.mongoRolePort = mongoRolePort;
        this.mongoUserPort = mongoUserPort;
        this.mongoNetworkingPort = mongoNetworkingPort;
    }

    @Override
    public Mono<UserResponse> registerUser(UserRegistrationCommand createUserCommand) {
        return null;
//                mongoUserPort.existsByUsernameOrEmail(createUserCommand.getUsername(), createUserCommand.getEmail())
//                .filter(isExisted -> !isExisted)
//                .switchIfEmpty(Mono.error(() -> {
//                    String exceptionMessage = translator.getLocalizedMessage(Constants.MessageKeys.EMAIL_DUPLICATE);
//                    return new DuplicateUniqueFieldException(exceptionMessage);
//                }))
//                .then(Mono.just(createUserCommand))
//                .map(command -> mapper.map(command, User.class))
//                .zipWith(mongoRolePort.getRoleByDefaultIsTrue())
//                .doOnNext(tuple -> {
//                    User user = tuple.getT1();
//                    user.setNetworking(new Networking(UUID.randomUUID().toString()));
//                    user.setRoles(Collections.singletonList(tuple.getT2()));
//                })
//                .map(Tuple2::getT1)
//                .flatMap(user -> mongoUserPort.save(user)
//                        .map(User::getNetworking)
//                        .flatMap(mongoNetworkingPort::save)
//                        .thenReturn(user)
//                ).doOnNext(user -> log.info("User {} saved to MongoDB", user.getId()))
//                .map(user -> mapper.map(user, UserResponse.class));
    }

    @Override
    public Mono<UserResponse> updateUserProfile(String userId, UpdateUserProfileCommand command) {
        return mongoUserPort.findById(userId)
                .switchIfEmpty(Mono.error(() -> {
                    String exceptionMessage = translator.getLocalizedMessage(Constants.MessageKeys.USER_NOT_FOUND);
                    return new NotFoundException(exceptionMessage);
                }))
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
