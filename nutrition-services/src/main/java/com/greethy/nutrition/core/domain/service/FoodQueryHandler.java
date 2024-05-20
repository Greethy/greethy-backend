package com.greethy.nutrition.core.domain.service;

import com.greethy.nutrition.core.port.in.query.*;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greethy.nutrition.api.rest.dto.response.FoodResponse;
import com.greethy.nutrition.core.port.out.FoodPort;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class FoodQueryHandler {

    private final ModelMapper mapper;

    private final FoodPort mongoPort;

    private final FoodPort redisPort;

    public FoodQueryHandler(ModelMapper mapper,
                            @Qualifier("mongoFoodAdapter") FoodPort mongoPort,
                            @Qualifier("redisFoodAdapter") FoodPort redisPort) {
        this.mapper = mapper;
        this.mongoPort = mongoPort;
        this.redisPort = redisPort;
    }

    @QueryHandler
    Mono<FoodResponse> handle(FindFoodByIdQuery query) {
        String foodId = query.getFoodId();

        return redisPort.findById(foodId)
                .switchIfEmpty(Mono.defer(() -> mongoPort.findById(foodId)
                        .flatMap(food -> redisPort.save(food).thenReturn(food)))
                )
                .map(food -> mapper.map(food, FoodResponse.class));
    }

    @QueryHandler
    Mono<Boolean> handle(CheckIfFoodExistsQuery query) {
        return mongoPort.existsById(query.getFoodId());
    }

    @QueryHandler
    Mono<Long> handle(CountAllFoodQuery query) {
        return mongoPort.countAll();
    }

    @QueryHandler
    Flux<FoodResponse> handle(FindAllFoodQuery query) {
        return redisPort.findAll()
                .switchIfEmpty(mongoPort.findAll())
                .map(food -> mapper.map(food, FoodResponse.class));
    }

    @QueryHandler
    Flux<FoodResponse> handle(FindFoodWithPaginationQuery query) {
        Pageable pageable = PageRequest.of(query.getOffset(), query.getLimit());
        return mongoPort.findAllBy(pageable)
                .map(food -> mapper.map(food, FoodResponse.class));
    }
}
