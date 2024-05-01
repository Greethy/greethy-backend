package com.greethy.user.core.domain.service;

import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.greethy.core.domain.query.CheckIfUserExistsQuery;
import com.greethy.core.domain.query.FindUserBodySpecsIdsQuery;
import com.greethy.user.api.rest.dto.response.UserResponse;
import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.port.in.query.*;
import com.greethy.user.core.port.out.read.CheckIfUserExistsPort;
import com.greethy.user.core.port.out.read.FindUserPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        return findUserPort.findAll().map(user -> mapper.map(user, UserResponse.class));
    }

    @QueryHandler
    Flux<UserResponse> handle(GetAllUserWithPageableQuery query) {
        return Flux.just(PageRequest.of(query.getOffset(), query.getLimit()))
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
    public Boolean handle(CheckIfUserEmailExistsQuery query, CheckIfUserExistsPort checkIfPort) {
        return checkIfPort.existsByEmail(query.getEmail());
    }

    @QueryHandler
    public Mono<Boolean> handle(CheckIfUserExistsQuery query, CheckIfUserExistsPort checkIfPort) {
        return checkIfPort.existsById(query.getUserId());
    }

    @QueryHandler
    public Mono<Boolean> handle(CheckIfUsernameOrEmailExistsQuery query, CheckIfUserExistsPort checkIfPort) {
        return checkIfPort.existsByUsernameOrEmail(query.getUsername(), query.getEmail());
    }

    @QueryHandler
    public Flux<String> handle(FindUserBodySpecsIdsQuery query) {
        return findUserPort
                .findById(query.getUserId())
                .map(User::getBodySpecsIds)
                .flatMapMany(Flux::fromIterable);
    }
}
