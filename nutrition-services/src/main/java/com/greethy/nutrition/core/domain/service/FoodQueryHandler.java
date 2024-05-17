package com.greethy.nutrition.core.domain.service;

import com.greethy.nutrition.api.rest.dto.response.FoodResponse;
import com.greethy.nutrition.core.port.in.query.CheckIfFoodExistsQuery;
import com.greethy.nutrition.core.port.in.query.CountAllFoodQuery;
import com.greethy.nutrition.core.port.in.query.FindFoodByIdQuery;
import com.greethy.nutrition.core.port.in.query.FindFoodWithPaginationQuery;
import com.greethy.nutrition.core.port.out.FoodPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodQueryHandler {

    private final FoodPort port;

    private final ModelMapper mapper;

    @QueryHandler
    Mono<FoodResponse> handle(FindFoodByIdQuery query) {
        return port.findById(query.getFoodId())
                .map(food -> mapper.map(food, FoodResponse.class));
    }

    @QueryHandler
    Mono<Boolean> handle(CheckIfFoodExistsQuery query) {
        return port.existsById(query.getFoodId());
    }

    @QueryHandler
    Mono<Long> handle(CountAllFoodQuery query) {
        return port.countAll();
    }

    @QueryHandler
    Flux<FoodResponse> handle(FindFoodWithPaginationQuery query) {
        Pageable pageable = PageRequest.of(query.getOffset(), query.getLimit());
        return port.findAllBy(pageable)
                .map(food -> mapper.map(food, FoodResponse.class));
    }
}
