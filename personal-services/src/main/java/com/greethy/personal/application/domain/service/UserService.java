package com.greethy.personal.application.domain.service;

import com.greethy.annotation.hexagonal.UseCase;
import com.greethy.personal.application.domain.entity.User;
import com.greethy.personal.application.domain.exception.DuplicateUniqueFieldException;
import com.greethy.personal.application.port.inbound.CreateUserUseCase;
import com.greethy.personal.application.port.inbound.FindAllUserUseCase;
import com.greethy.personal.application.port.inbound.UpdateUserUseCase;
import com.greethy.personal.application.port.outbound.CreateUserPort;
import com.greethy.personal.application.port.outbound.ExistsUserPort;
import com.greethy.personal.application.port.outbound.FindUserPort;
import com.greethy.personal.application.port.outbound.UpdateUserPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase, FindAllUserUseCase, UpdateUserUseCase {

    private final CreateUserPort saveUserPort;

    private final ExistsUserPort existsUserPort;

    private final FindUserPort findUserPort;

    private final UpdateUserPort updateUserPort;

    @Override
    public Mono<User> createUser(User user) {
        return existsUserPort.existsByUsernameOrEmail(user.getUsername(), user.getEmail())
                .flatMap(isExisted -> isExisted ? Mono.empty() : saveUserPort.create(user))
                .switchIfEmpty(Mono.error(new DuplicateUniqueFieldException("Username or email already taken !")));
    }

    @Override
    public Flux<User> findAll() {
        return findUserPort.findAll();
    }

    @Override
    public Mono<User> update(String id, User user) {
//        return existsUserPort.existsById(id)
//                .flatMap(isExisted -> isExisted ? Mono.empty() : findUserPort.findById(id))
//                .switchIfEmpty(Mono.error(new Exception()))
//                .map(foundUser -> )
//                ;
        return null;
    }

}
