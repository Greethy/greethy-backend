package com.greethy.nutritionquery.domain.service;

import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.response.MenuResponse;
import reactor.core.publisher.Mono;

public interface MenuQueryService {

    Mono<MenuResponse> getMenuById(GetByIdQuery query);

    Mono<MenuResponse> suggestMenuFoods(String username);
}
