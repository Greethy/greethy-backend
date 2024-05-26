package com.greethy.user.core.domain.service;

import com.greethy.core.domain.event.UserBodySpecsAddedEvent;
import com.greethy.core.domain.event.UserBodySpecsDeletedEvent;
import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.event.UserDeletedEvent;
import com.greethy.user.core.event.UserRegisteredEvent;
import com.greethy.user.core.event.UserUpdatedEvent;
import com.greethy.user.core.event.VerificationEmailSentEvent;
import com.greethy.user.core.port.out.UserPort;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * @author Kien N.Thanh
 */
@Slf4j
@Service
@ProcessingGroup("user-group")
public class UserEventHandler {

    private final ModelMapper mapper;

    private final UserPort mongoUserPort;

    public UserEventHandler(ModelMapper mapper,
                            @Qualifier("mongoUserAdapter") UserPort mongoUserPort) {
        this.mapper = mapper;
        this.mongoUserPort = mongoUserPort;
    }


    @EventHandler
    public void on(UserRegisteredEvent event) {
        Mono.just(event)
                .map(userRegisteredEvent -> mapper.map(userRegisteredEvent, User.class))
                .flatMap(mongoUserPort::save)
                .subscribe(user -> log.info("User {} has been created", user.getId()));
    }

    @EventHandler
    public void on(VerificationEmailSentEvent event) {}

    @EventHandler
    public void on(UserUpdatedEvent event) {
        String userId = event.getUserId();
        mongoUserPort.findById(userId)
                .doOnNext(user -> {
                    user.setAvatar(event.getAvatar());
                    user.setBannerImage(event.getBannerImage());
                    user.setBio(event.getBio());
                    user.setPersonalDetail(event.getPersonalDetail());
                })
                .doOnNext(user -> user.setUpdatedAt(LocalDateTime.now()))
                .flatMap(mongoUserPort::save)
                .subscribe(user -> log.info("User {} has been updated", user.getId()));
    }

    @EventHandler
    public void on(UserDeletedEvent event) {
        Mono.just(event)
                .map(UserDeletedEvent::getUserId)
                .flatMap(mongoUserPort::deleteById)
                .then(Mono.just(event.getUserId()))
                .subscribe();
    }

    @EventHandler
    void on(UserBodySpecsAddedEvent event) {
        mongoUserPort.findById(event.getUserId())
                .doOnNext(user -> {
                    user.getBodySpecsIds().add(event.getBodySpecsId());
                    user.setUpdatedAt(LocalDateTime.now());
                })
                .flatMap(mongoUserPort::save)
                .subscribe(user -> log.info("user has been created a new BodySpecs {}", event.getBodySpecsId()));
    }

    @EventHandler
    void on(UserBodySpecsDeletedEvent event) {
        String userId = event.getUserId();
        mongoUserPort.findById(userId)
                .doOnNext(user -> user.getBodySpecsIds().remove(event.getBodySpecsId()))
                .flatMap(mongoUserPort::save)
                .subscribe(user -> log.info("user {} delete BodySpecs {}", event.getUserId(), event.getBodySpecsId()));
    }
}
