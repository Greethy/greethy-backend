package com.greethy.nutritionquery.domain.service.impl;

import com.greethy.common.infra.component.i18n.Translator;
import com.greethy.nutritioncommon.constant.Constants;
import com.greethy.nutritioncommon.dto.response.FoodIdResponse;
import com.greethy.nutritioncommon.exception.NotFoundException;
import com.greethy.nutritionquery.domain.port.FoodPort;
import com.greethy.nutritionquery.domain.service.FoodQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class FoodQueryServiceImpl implements FoodQueryService {

    private final Translator translator;

    private final FoodPort mongoFoodPort;

    public FoodQueryServiceImpl(Translator translator,
                                FoodPort mongoFoodPort) {
        this.translator = translator;
        this.mongoFoodPort = mongoFoodPort;
    }

    @Override
    public Flux<FoodIdResponse> getAllFoodIds() {
        return mongoFoodPort.findAll()
                .switchIfEmpty(Mono.error(() -> {
                    String message = translator.getLocalizedMessage(Constants.MessageKeys.BODY_SPEC_NOT_FOUND);
                    return new NotFoundException(message);
                })).map(food -> new FoodIdResponse(food.getId()));
    }

}
