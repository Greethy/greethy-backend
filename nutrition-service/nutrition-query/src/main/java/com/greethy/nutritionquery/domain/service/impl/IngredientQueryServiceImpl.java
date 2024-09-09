package com.greethy.nutritionquery.domain.service.impl;

import com.greethy.common.infra.component.i18n.Translator;
import com.greethy.nutritioncommon.constant.Constants;
import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.request.query.GetByPaginationQuery;
import com.greethy.nutritioncommon.dto.request.query.SearchByNameQuery;
import com.greethy.nutritioncommon.dto.response.IngredientResponse;
import com.greethy.nutritioncommon.exception.NotFoundException;
import com.greethy.nutritionquery.domain.port.IngredientPort;
import com.greethy.nutritionquery.domain.service.IngredientQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientQueryServiceImpl implements IngredientQueryService {

    private final ModelMapper mapper;

    private final Translator translator;

    private final IngredientPort ingredientPort;

    @Override
    public Mono<IngredientResponse> getIngredientById(GetByIdQuery query) {
        return ingredientPort.findById(query.id())
                .switchIfEmpty(Mono.error(() -> {
                    var message = translator.getLocalizedMessage(Constants.MessageKeys.FOOD_NOT_FOUND);
                    return new NotFoundException(message);
                })).map(ingredient -> mapper.map(ingredient, IngredientResponse.class));
    }

    @Override
    public Flux<IngredientResponse> getIngredientsByPagination(GetByPaginationQuery query) {
        var sort = StringUtils.startsWith(query.sort(), "-")
                ? Sort.by(query.sort().substring(1)).descending()
                : Sort.by(query.sort().substring(1)).ascending();
        var pageable = PageRequest.of(query.offset(), query.limit(), sort);
        return ingredientPort.findAllBy(pageable)
                .switchIfEmpty(Mono.error(this::ingredientNotFound))
                .map(ingredient -> mapper.map(ingredient, IngredientResponse.class));
    }

    @Override
    public Flux<IngredientResponse> getIngredientsByGroup(SearchByNameQuery query) {
        var sort = StringUtils.startsWith(query.sort(), "-")
                ? Sort.by(query.sort().substring(1)).descending()
                : Sort.by(query.sort().substring(1)).ascending();
        var pageable = PageRequest.of(query.offset(), query.limit(), sort);
        return ingredientPort.findByGroup(query.name(), pageable)
                .switchIfEmpty(Mono.error(this::ingredientNotFound))
                .map(ingredient -> mapper.map(ingredient, IngredientResponse.class));
    }

    private NotFoundException ingredientNotFound() {
        var message = translator.getLocalizedMessage(Constants.MessageKeys.INGREDIENT_NOT_FOUND);
        return new NotFoundException(message);
    }

}
