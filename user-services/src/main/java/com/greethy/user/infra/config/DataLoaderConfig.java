package com.greethy.user.infra.config;

import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.port.out.UserPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class DataLoaderConfig {

    private final PasswordEncoder encoder;

    private final UserPort userPort;

    public DataLoaderConfig(PasswordEncoder encoder, UserPort userPort) {
        this.encoder = encoder;
        this.userPort = userPort;
    }

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
