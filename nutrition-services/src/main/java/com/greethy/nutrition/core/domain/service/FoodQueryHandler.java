package com.greethy.nutrition.core.domain.service;

import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greethy.nutrition.api.rest.dto.response.FoodResponse;
import com.greethy.nutrition.core.port.in.query.CheckIfFoodExistsQuery;
import com.greethy.nutrition.core.port.in.query.CountAllFoodQuery;
import com.greethy.nutrition.core.port.in.query.FindFoodByIdQuery;
import com.greethy.nutrition.core.port.in.query.FindFoodWithPaginationQuery;
import com.greethy.nutrition.core.port.out.read.CheckIfFoodPort;
import com.greethy.nutrition.core.port.out.read.FindFoodPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodQueryHandler {

    private final ModelMapper mapper;

    private final FindFoodPort findFoodPort;

    @QueryHandler
    Mono<FoodResponse> handle(FindFoodByIdQuery query) {
        return findFoodPort.findById(query.getFoodId()).map(food -> mapper.map(food, FoodResponse.class));
    }

    @QueryHandler
    Mono<Boolean> handle(CheckIfFoodExistsQuery query, CheckIfFoodPort foodPort) {
        return foodPort.existsById(query.getFoodId());
    }

    @QueryHandler
    Mono<Long> handle(CountAllFoodQuery query, FindFoodPort foodPort) {
        return foodPort.countAll();
    }

    @QueryHandler
    Flux<FoodResponse> handle(FindFoodWithPaginationQuery query) {
        Pageable pageable = PageRequest.of(query.getOffset(), query.getLimit());
        return findFoodPort.findAllBy(pageable).map(food -> mapper.map(food, FoodResponse.class));
    }
}
