package com.greethy.userquery.domain.service;

import com.greethy.usercommon.dto.request.query.GetCurrentUserProfileQuery;
import com.greethy.usercommon.dto.request.query.GetUserByIdQuery;
import com.greethy.usercommon.dto.request.query.GetUserByUsernameOrEmailQuery;
import com.greethy.usercommon.dto.response.UserProfileResponse;
import com.greethy.usercommon.dto.response.UserResponse;
import reactor.core.publisher.Mono;

public interface UserQueryService {

    Mono<UserProfileResponse> getUserProfileByUsernameOrEmail(GetCurrentUserProfileQuery query);

    Mono<UserResponse> getUserById(GetUserByIdQuery query);

    Mono<UserResponse> getUserByUsernameOrEmail(GetUserByUsernameOrEmailQuery query);

}
