package com.greethy.personal.domain.service;

import com.greethy.annotation.hexagonal.UseCase;
import com.greethy.personal.domain.entity.User;
import com.greethy.personal.domain.exception.DuplicateUniqueFieldException;
import com.greethy.personal.domain.port.inbound.FindAllUserUseCase;
import com.greethy.personal.domain.port.inbound.CreateUserUseCase;
import com.greethy.personal.domain.port.outbound.CreateUserPort;
import com.greethy.personal.domain.port.outbound.ExistsUserPort;
import com.greethy.personal.domain.port.outbound.FindUserPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase, FindAllUserUseCase {

    private final CreateUserPort saveUserPort;

    private final ExistsUserPort existsUserPort;

    private final FindUserPort findUserPort;

    @Override
    public Mono<User> createUser(User user) {
        return existsUserPort.existsByUsernameOrEmail(user.getUsername(), user.getEmail())
                .flatMap(isExisted -> isExisted ? Mono.error(new DuplicateUniqueFieldException("Username or email already used !")) : saveUserPort.create(user))
                ;
    }

    @Override
    public Flux<User> findAll() {
        return findUserPort.findAll();
    }
}
