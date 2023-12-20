package com.greethy.personal.domain.service;

import com.greethy.annotation.hexagonal.UseCase;
import com.greethy.personal.domain.entity.User;
import com.greethy.personal.domain.port.inbound.FindAllUserUseCase;
import com.greethy.personal.domain.port.inbound.SaveUserUseCase;
import com.greethy.personal.domain.port.outbound.CreateUserPort;
import com.greethy.personal.domain.port.outbound.FindUserPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class UserService implements SaveUserUseCase, FindAllUserUseCase {

    private final CreateUserPort saveUserPort;

    private final FindUserPort findUserPort;

    @Override
    public Mono<User> saveUser(User user) {
        return saveUserPort.create(user);
    }

    @Override
    public Flux<User> findAll() {
        return findUserPort.findAll();
    }
}
