package com.greethy.user.infra.config;

import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.port.out.write.SaveUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoaderConfig {

    private final @Qualifier("mongodb-save-adapter") SaveUserPort userPort;

    private final PasswordEncoder encoder;

    @EventListener(value = ApplicationReadyEvent.class)
    void init() {
        log.info("Start data initialization....");

        Mono.just(new User())
                .doOnNext(user -> {
                    user.setId("admin");
                    user.setUsername("admin");
                    user.setEmail("admin123@gmail.com");
                    user.setPassword(encoder.encode("admin"));
                    user.setVerified(true);
                }).flatMap(userPort::save)
                .subscribe();
    }

}
