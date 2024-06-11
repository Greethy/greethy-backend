package com.greethy.usercommand;

import com.greethy.usercommon.constant.Constants;
import com.greethy.usercommon.entity.Networking;
import com.greethy.usercommon.entity.Role;
import com.greethy.usercommon.entity.User;
import com.greethy.usercommon.entity.enums.Permission;
import com.greethy.usercommon.entity.value.PersonalDetail;
import com.greethy.usercommon.repository.mongodb.NetworkingRepository;
import com.greethy.usercommon.repository.mongodb.RoleRepository;
import com.greethy.usercommon.repository.mongodb.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(
        scanBasePackages = {
                "com.greethy.common",
                "com.greethy.usercommon",
                "com.greethy.usercommand"
        })
public class UserCommandApplication implements CommandLineRunner {

    private final PasswordEncoder encoder;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final NetworkingRepository networkingRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserCommandApplication.class, args);
    }

    @Override
    public void run(String... args) {
        roleRepository.deleteAll()
                .thenMany(Flux.fromIterable(roles()))
                .flatMap(roleRepository::save)
                .subscribe(role -> log.info("Role: {} has been stored in MongoDB", role));

        var count = userRepository.count().block();
        assert count != null;
        if (count != 1000) {
            Mono.when(userRepository.deleteAll(), networkingRepository.deleteAll())
                    .subscribeOn(Schedulers.boundedElastic())
                    .thenMany(
                            Flux.interval(Duration.ofMillis(10))
                                    .take(1000)
                                    .flatMap(this::createUser)
                                    .parallel()
                                    .runOn(Schedulers.parallel())
                                    .flatMap(tuple2 -> Mono.when(
                                                    userRepository.save(tuple2.getT1()),
                                                    networkingRepository.save(tuple2.getT2())
                                            ).thenReturn(tuple2.getT1())
                                    )
                    ).subscribe(
                            user -> log.info("Fake user: {} has been stored in MongoDB", user),
                            error -> log.error("Error occurred during user saving: ", error),
                            () -> log.info("All users have been processed and saved.")
                    );
        }
    }

    private Mono<Tuple2<User, Networking>> createUser(Long i) {
        var faker = new Faker();
        var user = new User();
        var networking = new Networking(UUID.randomUUID().toString());
        user.setUsername(faker.internet().username());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(encoder.encode("123456"));
        user.setAvatarUrl(faker.internet().image());
        user.setBannerUrl(faker.internet().image());
        user.setBio("This is Bio");
        user.setVerified(true);
        user.setNetworkingId(networking.getId());
        user.setRoles(Collections.singletonList(new Role("1", Constants.RoleType.ROLE_REGULAR, true, Set.of(Permission.READ))));
        user.setPersonalDetail(new PersonalDetail(
                faker.name().fullName(),
                faker.date().birthdayLocalDate(10, 50),
                faker.address().fullAddress(),
                faker.internet().webdomain(),
                faker.gender().types())
        );
        return Mono.zip(Mono.just(user), Mono.just(networking));
    }

    private Set<Role> roles() {
        var roles = new HashSet<Role>();
        roles.add(new Role("1", Constants.RoleType.ROLE_REGULAR, true, Set.of(Permission.READ)));
        roles.add(new Role("2", Constants.RoleType.ROLE_PREMIUM, false, Set.of(Permission.READ)));
        roles.add(new Role("3", Constants.RoleType.ROLE_EXPERT, false, Set.of(Permission.READ)));
        roles.add(new Role("4", Constants.RoleType.ROLE_MERCHANT, false, Set.of(Permission.READ, Permission.DELETE)));
        roles.add(new Role("5", Constants.RoleType.ROLE_ADMIN, false, Set.of(Permission.READ, Permission.WRITE, Permission.DELETE)));
        return roles;
    }

}