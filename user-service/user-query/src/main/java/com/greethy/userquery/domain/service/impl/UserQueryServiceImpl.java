package com.greethy.userquery.domain.service.impl;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.common.infra.component.i18n.Translator;
import com.greethy.usercommon.constant.Constants;
import com.greethy.usercommon.dto.request.query.*;
import com.greethy.usercommon.dto.response.IdentityResponse;
import com.greethy.usercommon.dto.response.UserProfileResponse;
import com.greethy.usercommon.dto.response.UserResponse;
import com.greethy.usercommon.dto.value.NetworkingDto;
import com.greethy.usercommon.exception.NotFoundException;
import com.greethy.userquery.domain.port.NetworkingPort;
import com.greethy.userquery.domain.port.UserPort;
import com.greethy.userquery.domain.service.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
    public Mono<UserProfileResponse> getUserProfileByUsernameOrEmail(GetByUsernameOrEmailQuery query) {
        return mongoUserPort.findByUsernameOrEmail(query.usernameOrEmail())
                .switchIfEmpty(Mono.error(this::userNotFoundException))
                .flatMap(user -> mongoNetworkingPort.findById(user.getNetworkingId())
                        .map(networking -> {
                            var response = mapper.map(user, UserProfileResponse.class);
                            var networkingDto = mapper.map(networking, NetworkingDto.class);
                            response.setNetworking(networkingDto);
                            return response;
                        })
                );
    }

    @Override
    public Mono<IdentityResponse> getIdentityByUsernameOrEmail(GetByUsernameOrEmailQuery query) {
        return mongoUserPort.findByUsernameOrEmail(query.usernameOrEmail())
                .switchIfEmpty(Mono.error(this::userNotFoundException))
                .map(user -> mapper.map(user, IdentityResponse.class));
    }

    @Override
    public Mono<UserResponse> getUserById(GetByIdQuery query) {
        return mongoUserPort.findById(query.id())
                .switchIfEmpty(Mono.error(this::userNotFoundException))
                .flatMap(user -> mongoNetworkingPort.findById(user.getNetworkingId())
                        .map(networking -> {
                            var response = mapper.map(user, UserResponse.class);
                            var networkingDto = mapper.map(networking, NetworkingDto.class);
                            response.setNetworking(networkingDto);
                            return response;
                        })
                );
    }

    @Override
    public Mono<UserResponse> getUserByUsernameOrEmail(GetByUsernameOrEmailQuery query) {
        return mongoUserPort.findByUsernameOrEmail(query.usernameOrEmail())
                .switchIfEmpty(Mono.error(this::userNotFoundException))
                .flatMap(user -> mongoNetworkingPort.findById(user.getNetworkingId())
                        .map(networking -> {
                            var response = mapper.map(user, UserResponse.class);
                            var networkingDto = mapper.map(networking, NetworkingDto.class);
                            response.setNetworking(networkingDto);
                            return response;
                        })
                );
    }

    @Override
    public Mono<ObjectIdResponse> getUserIdByUsername(GetByUsernameOrEmailQuery query) {
        return mongoUserPort.findByUsernameOrEmail(query.usernameOrEmail())
                .switchIfEmpty(Mono.error(this::userNotFoundException))
                .map(user -> new ObjectIdResponse(user.getId()));
    }

    @Override
    public Flux<UserResponse> getAllUsersByPagination(GetByPaginationQuery query) {
        var sort = StringUtils.startsWith(query.sort(), "-")
                ? Sort.by(query.sort().substring(1)).descending()
                : Sort.by(query.sort().substring(1)).ascending();
        var pageable = PageRequest.of(query.offset(), query.limit(), sort);
        return mongoUserPort.findByPagination(pageable)
                .switchIfEmpty(Mono.error(this::userNotFoundException))
                .map(user -> mapper.map(user, UserResponse.class));
    }

    @Override
    public Flux<ObjectIdResponse> getAllUserIds() {
        return mongoUserPort.findAll()
                .switchIfEmpty(Mono.error(this::userNotFoundException))
                .map(user -> new ObjectIdResponse(user.getId()));
    }

    private NotFoundException userNotFoundException() {
        String message = translator.getLocalizedMessage(Constants.MessageKeys.USER_NOT_FOUND);
        return new NotFoundException(message);
    }

}
