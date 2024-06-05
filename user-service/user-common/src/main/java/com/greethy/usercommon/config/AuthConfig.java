package com.greethy.usercommon.config;

import com.greethy.core.infra.component.i18n.Translator;
import com.greethy.usercommon.constant.Constant;
import com.greethy.usercommon.entity.Role;
import com.greethy.usercommon.exception.InvalidUsernameOrEmailException;
import com.greethy.usercommon.repository.mongodb.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

@Configuration
public class AuthConfig {

    @Bean
    ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService,
                                                        PasswordEncoder passwordEncoder) {
        var authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(UserRepository userRepository, Translator translator) {
        return usernameOrEmail -> userRepository.findByUsernameOrEmail(usernameOrEmail)
                .switchIfEmpty(Mono.error(() -> {
                    String message = translator.getLocalizedMessage(Constant.MessageKeys.INVALID_USERNAME_OR_EMAIL);
                    return new InvalidUsernameOrEmailException(message);
                }))
                .map(user -> User.withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRoles()
                                .stream()
                                .map(Role::getName)
                                .toList()
                                .toArray(new String[0])
                        ).accountExpired(false)
                        .credentialsExpired(false)
                        .credentialsExpired(false)
                        .disabled(false)
                        .accountLocked(false)
                        .build());
    }

}
