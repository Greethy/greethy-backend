package com.greethy.nutritionquery.domain.service;

import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.response.BodySpecResponse;
import reactor.core.publisher.Mono;

public interface BodySpecQueryService {

    Mono<BodySpecResponse> findBodySpecById(GetByIdQuery query);

}
