package com.greethy.account;

import com.greethy.account.user.infrastructure.adapter.UserKeycloakAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = {"com.greethy.core", "com.greethy.account"})
public class AccountServiceApplication implements CommandLineRunner {

	private final UserKeycloakAdapter userKeycloakAdapter;

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		userKeycloakAdapter.getUsers()
				.subscribe(returnValue -> log.info("get keycloak users: {}", returnValue));
		userKeycloakAdapter.exists("greethy@gmail.com")
				.subscribe(exists -> log.info("exists: {}", exists));

	}
}
