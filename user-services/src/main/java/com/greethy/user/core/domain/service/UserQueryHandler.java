package com.greethy.user.core.domain.service;

import com.greethy.core.domain.query.CheckIfUserExistsQuery;
import com.greethy.core.domain.query.FindUserBodySpecsIdsQuery;
import com.greethy.user.api.rest.dto.response.UserResponse;
import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.port.in.query.*;
import com.greethy.user.core.port.out.UserPort;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserQueryHandler {

    private final ModelMapper mapper;


    private final UserPort userPort;

    public UserQueryHandler(ModelMapper mapper,
                            @Qualifier("mongoUserAdapter") UserPort userPort) {
        this.mapper = mapper;
        this.userPort = userPort;
    }

    @QueryHandler
    Flux<UserResponse> handle(FindAllUserQuery query) {
        return userPort.findAll().map(user -> mapper.map(user, UserResponse.class));
    }

    @QueryHandler
    Flux<UserResponse> handle(GetAllUserWithPageableQuery query) {
        var pageable = PageRequest.of(query.getOffset(), query.getLimit());
        return Flux.just(pageable)
                .flatMap(userPort::findAllBy)
                .map(user -> mapper.map(user, UserResponse.class));
    }

    @QueryHandler
    Mono<Long> handle(CountAllUserQuery query) {
        return userPort.countAll();
    }

    @QueryHandler
    Mono<UserResponse> handle(FindUserByIdQuery query) {
        return Mono.just(query.getUserId())
                .flatMap(userPort::findById)
                .map(user -> mapper.map(user, UserResponse.class));
    }

    @QueryHandler
    Mono<UserResponse> handle(FindUserByUsernameOrEmailQuery query) {
        return Mono.just(query.getUsernameOrEmail())
                .flatMap(userPort::findByUsernameOrEmail)
                .map(user -> mapper.map(user, UserResponse.class));
    }

    @QueryHandler
    public Boolean handle(CheckIfUserEmailExistsQuery query) {
        return userPort.existsByEmail(query.getEmail());
    }

    @QueryHandler
    public Mono<Boolean> handle(CheckIfUserExistsQuery query) {
        return userPort.existsById(query.getUserId());
    }

    @QueryHandler
    public Mono<Boolean> handle(CheckIfUsernameOrEmailExistsQuery query) {
        return userPort.existsByUsernameOrEmail(query.getUsername(), query.getEmail());
    }

    @QueryHandler
    public Flux<String> handle(FindUserBodySpecsIdsQuery query) {
        String userId = query.getUserId();
        return userPort.findById(userId)
                .map(User::getBodySpecsIds)
                .flatMapMany(Flux::fromIterable);
    }

}
