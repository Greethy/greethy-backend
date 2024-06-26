package com.greethy.nutritionquery.domain.service.impl;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.common.infra.component.i18n.Translator;
import com.greethy.nutritioncommon.constant.Constants;
import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.request.query.GetByPaginationQuery;
import com.greethy.nutritioncommon.dto.request.query.SearchByNameQuery;
import com.greethy.nutritioncommon.dto.response.FoodResponse;
import com.greethy.nutritioncommon.entity.enums.FoodLabel;
import com.greethy.nutritioncommon.exception.NotFoundException;
import com.greethy.nutritionquery.domain.port.FoodPort;
import com.greethy.nutritionquery.domain.service.FoodQueryService;
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
public class FoodQueryServiceImpl implements FoodQueryService {

    private final ModelMapper mapper;

    private final Translator translator;

    private final FoodPort mongoFoodPort;

    public FoodQueryServiceImpl(ModelMapper mapper,
                                Translator translator,
                                FoodPort mongoFoodPort) {
        this.mapper = mapper;
        this.translator = translator;
        this.mongoFoodPort = mongoFoodPort;
    }

    @Override
    public Mono<FoodResponse> getFoodById(GetByIdQuery query) {
        return mongoFoodPort.findById(query.id())
                .switchIfEmpty(Mono.error(() -> {
                    String message = translator.getLocalizedMessage(Constants.MessageKeys.FOOD_NOT_FOUND);
                    return new NotFoundException(message);
                }))
                .map(food -> mapper.map(food, FoodResponse.class));
    }

    @Override
    public Flux<FoodResponse> searchFoodByName(SearchByNameQuery query) {
        var sort = StringUtils.startsWith(query.sort(), "-")
                ? Sort.by(query.sort().substring(1)).descending()
                : Sort.by(query.sort().substring(1)).ascending();
        var pageable = PageRequest.of(query.offset(), query.limit(), sort);
        return mongoFoodPort.findByNameRegex(query.name(), pageable)
                .switchIfEmpty(Mono.error(this::foodNotFoundException))
                .map(food -> mapper.map(food, FoodResponse.class));
    }

    @Override
    public Flux<FoodResponse> getFoodsByPagination(GetByPaginationQuery query) {
        var sort = StringUtils.startsWith(query.sort(), "-")
                ? Sort.by(query.sort().substring(1)).descending()
                : Sort.by(query.sort().substring(1)).ascending();
        var pageable = PageRequest.of(query.offset(), query.limit(), sort);
        return mongoFoodPort.findByPagination(pageable)
                .switchIfEmpty(Mono.error(this::foodNotFoundException))
                .map(food -> mapper.map(food, FoodResponse.class));
    }

    @Override
    public Flux<ObjectIdResponse> getAllFoodIds() {
        return mongoFoodPort.findAll()
                .switchIfEmpty(Mono.error(this::foodNotFoundException))
                .map(food -> new ObjectIdResponse(food.getId()));
    }

    @Override
    public Flux<String> getAllFoodLabels() {
        return Flux.fromArray(FoodLabel.values())
                .map(FoodLabel::getLabel);
    }

    private NotFoundException foodNotFoundException() {
        String message = translator.getLocalizedMessage(Constants.MessageKeys.FOOD_NOT_FOUND);
        return new NotFoundException(message);
    }

}
