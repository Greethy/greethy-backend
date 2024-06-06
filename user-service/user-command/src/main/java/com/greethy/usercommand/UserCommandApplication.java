package com.greethy.usercommand;

import com.greethy.usercommon.constant.Constant;
import com.greethy.usercommon.entity.Role;
import com.greethy.usercommon.entity.enums.Permission;
import com.greethy.usercommon.repository.mongodb.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(
        scanBasePackages = {
                "com.greethy.common",
                "com.greethy.usercommon",
                "com.greethy.usercommand"
        })
public class UserCommandApplication implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserCommandApplication.class, args);
    }

    @Override
    public void run(String... args) {
        roleRepository.deleteAll()
                .thenMany(Flux.fromIterable(roles()))
                .flatMap(roleRepository::save)
                .subscribe(role -> log.info("Role: {} haves been stored in MongoDB", role));
    }

    private Set<Role> roles() {
        var roles = new HashSet<Role>();
        roles.add(new Role("1", Constant.RoleType.ROLE_REGULAR, true, Set.of(Permission.READ)));
        roles.add(new Role("2", Constant.RoleType.ROLE_PREMIUM, false, Set.of(Permission.READ)));
        roles.add(new Role("3", Constant.RoleType.ROLE_EXPERT, false, Set.of(Permission.READ)));
        roles.add(new Role("4", Constant.RoleType.ROLE_MERCHANT, false, Set.of(Permission.READ, Permission.DELETE)));
        roles.add(new Role("5", Constant.RoleType.ROLE_ADMIN, false, Set.of(Permission.READ, Permission.WRITE, Permission.DELETE)));
        return roles;
    }

}