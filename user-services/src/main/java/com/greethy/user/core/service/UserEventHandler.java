package com.greethy.user.core.service;

import com.greethy.user.infrastructure.entity.User;
import com.greethy.user.core.event.UserRegisteredEvent;
import com.greethy.user.core.port.out.CreateUserPort;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@ProcessingGroup("user-group")
public class UserEventHandler {

    private final ModelMapper mapper;

    private final CreateUserPort createUserPort;

    @EventHandler
    public void on(UserRegisteredEvent event) {
        var user = mapper.map(event, User.class);
        createUserPort.create(user)
                .subscribe();
    }

}
