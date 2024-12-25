package com.greethy.account.profile.domain.service.listener;

import com.greethy.account.common.event.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountListener {

    @EventListener(value = UserRegisteredEvent.class)
    public void on(UserRegisteredEvent event) {

        Mono.just(event)
                .subscribe(System.out::println);
    }

}
