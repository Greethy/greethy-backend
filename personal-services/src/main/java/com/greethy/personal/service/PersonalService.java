package com.greethy.personal.service;

import com.greethy.personal.dto.ProfileRequest;
import com.greethy.personal.entity.Profile;
import com.greethy.personal.entity.User;
import reactor.core.publisher.Mono;

public interface PersonalService {

    Mono<User> createNewProfile(String id, ProfileRequest request);

    Mono<Profile> getUserProfile(String id);
}
