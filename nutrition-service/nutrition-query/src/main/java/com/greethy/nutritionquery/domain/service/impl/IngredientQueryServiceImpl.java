package com.greethy.nutritionquery.domain.service.impl;

import com.greethy.common.infra.component.i18n.Translator;
import com.greethy.nutritioncommon.constant.Constants;
import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.response.IngredientResponse;
import com.greethy.nutritioncommon.exception.NotFoundException;
import com.greethy.nutritionquery.domain.port.IngredientPort;
import com.greethy.nutritionquery.domain.service.IngredientQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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

}
