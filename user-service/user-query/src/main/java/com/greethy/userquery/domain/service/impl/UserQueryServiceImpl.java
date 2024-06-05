package com.greethy.userquery.domain.service.impl;

import com.greethy.core.infra.component.i18n.Translator;
import com.greethy.usercommon.constant.Constant;
import com.greethy.usercommon.dto.request.query.GetCurrentUserProfileQuery;
import com.greethy.usercommon.dto.request.query.GetUserByIdQuery;
import com.greethy.usercommon.dto.request.query.GetUserByUsernameOrEmailQuery;
import com.greethy.usercommon.dto.response.UserProfileResponse;
import com.greethy.usercommon.dto.response.UserResponse;
import com.greethy.usercommon.dto.value.NetworkingDto;
import com.greethy.usercommon.exception.NotFoundException;
import com.greethy.userquery.domain.port.NetworkingPort;
import com.greethy.userquery.domain.port.UserPort;
import com.greethy.userquery.domain.service.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final ModelMapper mapper;

    private final Translator translator;

    private final UserPort mongoUserPort;

    private final NetworkingPort mongoNetworkingPort;

    public UserQueryServiceImpl(ModelMapper mapper,
                                Translator translator,
                                UserPort mongoUserPort,
                                NetworkingPort mongoNetworkingPort) {
        this.mapper = mapper;
        this.translator = translator;
        this.mongoUserPort = mongoUserPort;
        this.mongoNetworkingPort = mongoNetworkingPort;
    }


    @Override
    public Mono<UserProfileResponse> getUserProfileByUsernameOrEmail(GetCurrentUserProfileQuery query) {
        return mongoUserPort.findByUsernameOrEmail(query.getUsernameOrEmail())
                .switchIfEmpty(Mono.error(() -> {
                    String message = translator.getLocalizedMessage(Constant.MessageKeys.USER_NOT_FOUND);
                    return new NotFoundException(message);
                })).flatMap(user -> Mono.zip(Mono.just(user), mongoNetworkingPort.findById(user.getNetworkingId()))
                ).map(tuple -> {
                    var response = mapper.map(tuple.getT1(), UserProfileResponse.class);
                    var networkingDto = mapper.map(tuple.getT2(), NetworkingDto.class);
                    response.setNetworking(networkingDto);
                    return response;
                });
    }

    @Override
    public Mono<UserResponse> getUserById(GetUserByIdQuery query) {
        return mongoUserPort.findById(query.getUserId())
                .switchIfEmpty(Mono.error(() -> {
                    String message = translator.getLocalizedMessage(Constant.MessageKeys.USER_NOT_FOUND);
                    return new NotFoundException(message);
                })).flatMap(user -> Mono.zip(Mono.just(user), mongoNetworkingPort.findById(user.getNetworkingId()))
                ).map(tuple -> {
                    var response = mapper.map(tuple.getT1(), UserResponse.class);
                    var networkingDto = mapper.map(tuple.getT2(), NetworkingDto.class);
                    response.setNetworking(networkingDto);
                    return response;
                });
    }

    @Override
    public Mono<UserResponse> getUserByUsernameOrEmail(GetUserByUsernameOrEmailQuery query) {
        return mongoUserPort.findByUsernameOrEmail(query.getUsernameOrEmail())
                .switchIfEmpty(Mono.error(() -> {
                    String message = translator.getLocalizedMessage(Constant.MessageKeys.USER_NOT_FOUND);
                    return new NotFoundException(message);
                })).flatMap(user -> Mono.zip(Mono.just(user), mongoNetworkingPort.findById(user.getNetworkingId()))
                ).map(tuple -> {
                    var response = mapper.map(tuple.getT1(), UserResponse.class);
                    var networkingDto = mapper.map(tuple.getT2(), NetworkingDto.class);
                    response.setNetworking(networkingDto);
                    return response;
                });
    }
}
