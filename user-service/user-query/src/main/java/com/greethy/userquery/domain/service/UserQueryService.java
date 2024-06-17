package com.greethy.userquery.domain.service;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.usercommon.dto.request.query.*;
import com.greethy.usercommon.dto.response.IdentityResponse;
import com.greethy.usercommon.dto.response.UserProfileResponse;
import com.greethy.usercommon.dto.response.UserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserQueryService {

    Mono<UserResponse> getUserById(GetByIdQuery query);

    Mono<UserResponse> getUserByUsernameOrEmail(GetByUsernameOrEmailQuery query);

    Mono<UserProfileResponse> getUserProfileByUsernameOrEmail(GetByUsernameOrEmailQuery query);

    Mono<IdentityResponse> getIdentityByUsernameOrEmail(GetByUsernameOrEmailQuery query);

    Mono<ObjectIdResponse> getUserIdByUsername(GetByUsernameOrEmailQuery query);

    Flux<UserResponse> getAllUsersByPagination(GetByPaginationQuery query);

    Flux<ObjectIdResponse> getAllUserIds();

}
