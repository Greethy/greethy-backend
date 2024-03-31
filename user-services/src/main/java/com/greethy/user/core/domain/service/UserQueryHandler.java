package com.greethy.user.core.domain.service;

import com.greethy.core.domain.query.FindUserBodySpecsIdsQuery;
import com.greethy.user.api.rest.dto.response.UserResponse;
import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.port.in.query.*;
import com.greethy.user.core.port.out.CheckIfExistsUserPort;
import com.greethy.user.core.port.out.FindUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserQueryHandler {

    private final ModelMapper mapper;

    private final FindUserPort findUserPort;

    @QueryHandler
    Flux<UserResponse> handle(FindAllUserQuery query) {
        return findUserPort.findAll()
                .map(user -> mapper.map(user, UserResponse.class));
    }

    @QueryHandler
    Flux<UserResponse> handle(GetAllUserWithPageableQuery query) {
        return Flux.just(PageRequest.of(query.getPage(), query.getSize()))
                .flatMap(findUserPort::findAllBy)
                .map(user -> mapper.map(user, UserResponse.class));
    }

    @QueryHandler
    Mono<Long> handle(CountAllUserQuery query) {
        return findUserPort.countAll();
    }

    @QueryHandler
    Mono<UserResponse> handle(FindUserByIdQuery query) {
        return Mono.just(query.getUserId())
                .flatMap(findUserPort::findById)
                .map(user -> mapper.map(user, UserResponse.class));
    }

    @QueryHandler
    Mono<UserResponse> handle(FindUserByUsernameOrEmailQuery query) {
        return Mono.just(query.getUsernameOrEmail())
                .flatMap(findUserPort::findByUsernameOrEmail)
                .map(user -> mapper.map(user, UserResponse.class));
    }

    @QueryHandler
    public Boolean handle(CheckIfUserEmailExistsQuery query,
                          CheckIfExistsUserPort port) {
        return port.existsByEmail(query.getEmail());
    }

    @QueryHandler
    public Flux<String> handle(FindUserBodySpecsIdsQuery query) {
        return findUserPort.findById(query.getUserId())
                .map(User::getBodySpecsIds)
                .flatMapMany(Flux::fromIterable);
    }

}
