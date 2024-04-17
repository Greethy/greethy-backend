package com.greethy.user.core.domain.service;

import com.greethy.core.domain.event.UserBodySpecsAddedEvent;
import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.domain.event.UserDeletedEvent;
import com.greethy.user.core.domain.event.UserRegisteredEvent;
import com.greethy.user.core.domain.event.UserUpdatedEvent;
import com.greethy.user.core.domain.event.VerificationEmailSentEvent;
import com.greethy.user.core.port.out.DeleteUserPort;
import com.greethy.user.core.port.out.FindUserPort;
import com.greethy.user.core.port.out.SaveUserPort;
import io.gorse.gorse4j.Gorse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 *
 * @author Kien N.Thanh
 * */
@Slf4j
@Service
@RequiredArgsConstructor
@ProcessingGroup("user-group")
public class UserEventHandler {

    private final ModelMapper mapper;

    @Qualifier("mongodb-create-adapter")
    private final SaveUserPort saveUserPort;

    private final FindUserPort findUserPort;

    private final DeleteUserPort deleteUserPort;

    private final Gorse gorse;

    @EventHandler
    public void on(UserRegisteredEvent event) {
        Mono.just(event)
                .map(userRegisteredEvent -> mapper.map(userRegisteredEvent, User.class))
                .flatMap(saveUserPort::save)
                .subscribe(user -> log.info("User " + user + " has been created"));
    }

    @EventHandler
    public void on(VerificationEmailSentEvent event) {
    }

    @EventHandler
    public void on(UserUpdatedEvent event) {
        findUserPort.findById(event.getUserId())
                .doOnNext(user -> {
                    user.setAvatar(event.getAvatar());
                    user.setBannerImage(event.getBannerImage());
                    user.setBio(event.getBio());
                    user.setPersonalDetail(event.getPersonalDetail());
                })
                .doOnNext(user -> user.setUpdatedAt(LocalDateTime.now()))
                .flatMap(saveUserPort::save)
                .subscribe(user -> log.info("UserAggregate: " + user + " updated successfully "));
    }

    @EventHandler
    public void on(UserDeletedEvent event) {
        Mono.just(event)
                .map(UserDeletedEvent::getUserId)
                .flatMap(deleteUserPort::deleteById)
                .subscribe();
    }

    @EventHandler
    void on(UserBodySpecsAddedEvent event) {
        findUserPort.findById(event.getUserId())
                .doOnNext(user -> {
                    user.getBodySpecsIds().add(event.getBodySpecsId());
                    user.setUpdatedAt(LocalDateTime.now());
                })
                .flatMap(saveUserPort::save)
                .subscribe(user -> log.info("user has been created a new BodySpecs {}", event.getBodySpecsId()));
    }

}
