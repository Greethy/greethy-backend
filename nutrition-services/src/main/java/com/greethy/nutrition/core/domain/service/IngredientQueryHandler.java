package com.greethy.nutrition.core.domain.service;

import com.greethy.nutrition.api.rest.dto.response.IngredientResponse;
import com.greethy.nutrition.core.port.in.query.CountAllIngredientQuery;
import com.greethy.nutrition.core.port.in.query.FindAllIngredientsQuery;
import com.greethy.nutrition.core.port.in.query.FindIngredientsWithPaginationQuery;
import com.greethy.nutrition.core.port.in.query.FindIngredientByIdQuery;
import com.greethy.nutrition.core.port.out.read.FindIngredientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientQueryHandler {

    private final FindIngredientPort findIngredientPort;

    private final ModelMapper mapper;

    @QueryHandler
    Mono<IngredientResponse> handle(FindIngredientByIdQuery query) {
        return findIngredientPort.findById(query.getIngredientId())
                .map(ingredient -> mapper.map(ingredient, IngredientResponse.class));
    }

    @QueryHandler
    Flux<IngredientResponse> handle(FindAllIngredientsQuery query) {
        return findIngredientPort.findAll()
                .map(ingredient -> mapper.map(ingredient, IngredientResponse.class));
    }

    @QueryHandler
    Flux<IngredientResponse> handle(FindIngredientsWithPaginationQuery query) {
        return Flux.just(PageRequest.of(query.getOffset(), query.getLimit()))
                .flatMap(findIngredientPort::findByPagination)
                .map(ingredient -> mapper.map(ingredient, IngredientResponse.class));
    }

    @QueryHandler
    Mono<Long> handle(CountAllIngredientQuery query) {
        return findIngredientPort.countAll();
    }

}
