package com.greethy.nutrition.core.domain.service;

import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.greethy.nutrition.api.rest.dto.response.BodySpecsResponse;
import com.greethy.nutrition.core.port.in.query.*;
import com.greethy.nutrition.core.port.out.BodySpecsPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BodySpecsQueryHandler {

    private final ModelMapper mapper;

    private final BodySpecsPort port;

    @QueryHandler
    Flux<BodySpecsResponse> handle(FindAllBodySpecsQuery query) {
        return port.findAll()
                .map(bodySpecs -> mapper.map(bodySpecs, BodySpecsResponse.class));
    }

    @QueryHandler
    Mono<BodySpecsResponse> handle(FindBodySpecsByIdQuery query) {
        return port
                .findById(query.getBodySpecsId())
                .map(bodySpecs -> mapper.map(bodySpecs, BodySpecsResponse.class));
    }

    @QueryHandler
    Flux<BodySpecsResponse> handle(FindBodySpecsWithPaginationQuery query) {
        return Flux.just(PageRequest.of(query.getOffset(), query.getLimit()))
                .flatMap(port::findAllBy)
                .map(bodySpecs -> mapper.map(bodySpecs, BodySpecsResponse.class));
    }

    @QueryHandler
    Mono<Long> handle(CountAllBodySpecsQuery query) {
        return port.countAll();
    }

    @QueryHandler
    Flux<BodySpecsResponse> handle(FindAllBodySpecsByIdQuery query) {
        return port
                .findAllByIds(Flux.fromIterable(query.getBodySpecsIds()))
                .map(bodySpecs -> mapper.map(bodySpecs, BodySpecsResponse.class));
    }
}
