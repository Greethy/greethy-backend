package com.greethy.personal.service.impl;

import com.greethy.personal.dto.ProfileRequest;
import com.greethy.personal.entity.Profile;
import com.greethy.personal.entity.User;
import com.greethy.personal.repository.UserRepository;
import com.greethy.personal.service.PersonalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonalServiceImpl implements PersonalService {

    private final ModelMapper mapper;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Mono<User> createNewProfile(String id, ProfileRequest request) {
        return userRepository.findById(id)
                .map(user -> {
                    var profile = mapper.map(request, Profile.class);
                    user.setProfile(profile);
                    return user;
                })
                .flatMap(userRepository::save)
                .doOnError(throwable -> log.error(throwable.getMessage()));
    }

    @Override
    public Mono<Profile> getUserProfile(String id) {
        return userRepository.findById(id)
                .map(User::getProfile);
    }

}
