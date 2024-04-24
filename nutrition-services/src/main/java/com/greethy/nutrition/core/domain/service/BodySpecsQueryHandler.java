package com.greethy.nutrition.core.domain.service;

import com.greethy.nutrition.api.rest.dto.response.BodySpecsResponse;
import com.greethy.nutrition.core.port.in.query.*;
import com.greethy.nutrition.core.port.out.read.FindBodySpecsPort;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BodySpecsQueryHandler {

    private final ModelMapper mapper;

    private final FindBodySpecsPort findBodySpecsPort;

    @QueryHandler
    Flux<BodySpecsResponse> handle(FindAllBodySpecsQuery query) {
        return findBodySpecsPort.findAll()
                .map(bodySpecs -> mapper.map(bodySpecs, BodySpecsResponse.class));
    }

    @QueryHandler
    Mono<BodySpecsResponse> handle(FindBodySpecsByIdQuery query) {
        return findBodySpecsPort.findById(query.getBodySpecsId())
                .map(bodySpecs -> mapper.map(bodySpecs, BodySpecsResponse.class));
    }

    @QueryHandler
    Flux<BodySpecsResponse> handle(FindBodySpecsByPaginationQuery query) {
        return Flux.just(PageRequest.of(query.getOffset(), query.getLimit()))
                .flatMap(findBodySpecsPort::findAllBy)
                .map(bodySpecs -> mapper.map(bodySpecs, BodySpecsResponse.class));
    }

    @QueryHandler
    Mono<Long> handle(CountAllBodySpecsQuery query) {
        return findBodySpecsPort.countAll();
    }

    @QueryHandler
    Flux<BodySpecsResponse> handle(FindAllBodySpecsByIdQuery query) {
        return findBodySpecsPort.findAllByIds(Flux.fromIterable(query.getBodySpecsIds()))
                .map(bodySpecs -> mapper.map(bodySpecs, BodySpecsResponse.class));
    }
}
