package com.greethy.userquery.domain.service;

import com.greethy.usercommon.dto.request.query.GetUserByIdQuery;
import com.greethy.usercommon.dto.response.UserResponse;
import reactor.core.publisher.Mono;

public interface UserQueryService {

    Mono<UserResponse> getUserById(GetUserByIdQuery query);

}
