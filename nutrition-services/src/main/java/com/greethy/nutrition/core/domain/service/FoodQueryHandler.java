package com.greethy.nutrition.core.domain.service;

import com.greethy.nutrition.api.rest.dto.response.IngredientResponse;
import com.greethy.nutrition.core.port.in.query.FindAllIngredientQuery;
import com.greethy.nutrition.core.port.out.food.FindIngredientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodQueryHandler {

    private final FindIngredientPort findIngredientPort;

    private final ModelMapper mapper;

    @QueryHandler
    Flux<IngredientResponse> handle(FindAllIngredientQuery query) {
        return findIngredientPort.findAll()
                .map(ingredient -> mapper.map(ingredient, IngredientResponse.class));
    }

}
