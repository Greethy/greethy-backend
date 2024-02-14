package com.greethy.user.core.service;

import com.greethy.user.core.domain.exception.DuplicateUniqueFieldException;
import com.greethy.user.core.event.UserRegisteredEvent;
import com.greethy.user.core.port.out.CheckIfExistsUserPort;
import com.greethy.user.core.port.out.CreateUserPort;
import com.greethy.user.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@ProcessingGroup("user-group")
public class UserEventHandler {

    private final ModelMapper mapper;

    @Qualifier("mongodb-create-adapter")
    private final CreateUserPort createUserPort;

    private final PasswordEncoder passwordEncoder;

    @Qualifier("mongodb-check-adapter")
    private final CheckIfExistsUserPort checkIfExistsUserPort;


    @EventHandler
    public void on(UserRegisteredEvent event) {
        checkIfExistsUserPort.existsByUsernameOrEmail(event.getUsername(), event.getEmail())
                .flatMap(isExisted -> {
                    if (isExisted) {
                        var error = new DuplicateUniqueFieldException("");
                        return Mono.error(error);
                    }
                    var user = mapper.map(event, User.class);
                    return Mono.just(user);
                })
                .doOnNext(user -> {
                    String hashedPassword = passwordEncoder.encode(user.getPassword());
                    user.setPassword(hashedPassword);
                })
                .flatMap(createUserPort::create)

                .subscribe(user -> log.info("User" + user.getId() + "has been created"));
    }

}
