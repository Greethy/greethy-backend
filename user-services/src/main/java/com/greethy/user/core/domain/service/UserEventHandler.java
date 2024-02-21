package com.greethy.user.core.domain.service;

import com.greethy.user.core.event.UserDeletedEvent;
import com.greethy.user.core.event.UserRegisteredEvent;
import com.greethy.user.core.event.VerificationEmailSentEvent;
import com.greethy.user.core.port.out.CreateUserPort;
import com.greethy.user.core.port.out.DeleteUserPort;
import com.greethy.user.infrastructure.entity.Role;
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

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
@ProcessingGroup("user-group")
public class UserEventHandler {

    private final ModelMapper mapper;

    @Qualifier("mongodb-create-adapter")
    private final CreateUserPort createUserPort;

    private final DeleteUserPort deleteUserPort;

    private final PasswordEncoder passwordEncoder;

    @EventHandler
    public void on(UserRegisteredEvent event) {
        Mono.just(event)
                .map(userRegisteredEvent -> mapper.map(userRegisteredEvent, User.class))
                .doOnNext(user -> {
                    String hashedPassword = passwordEncoder.encode(user.getPassword());
                    user.setPassword(hashedPassword);
                    user.setRoles(Collections.singletonList(Role.ROLE_USER));
                })
                .flatMap(createUserPort::create)
                .subscribe(user -> log.info("User " + user.getId() + " has been created"));
    }

    @EventHandler
    public void on(VerificationEmailSentEvent event) {

    }

    @EventHandler
    public void on(UserDeletedEvent event) {

    }

}
