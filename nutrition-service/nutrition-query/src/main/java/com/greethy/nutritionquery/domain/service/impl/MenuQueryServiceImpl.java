package com.greethy.nutritionquery.domain.service.impl;

import com.greethy.common.infra.component.i18n.Translator;
import com.greethy.nutritioncommon.constant.Constants;
import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.response.GorseSimilarityResponse;
import com.greethy.nutritioncommon.dto.response.MenuResponse;
import com.greethy.nutritioncommon.entity.Menu;
import com.greethy.nutritioncommon.entity.enums.FoodGroup;
import com.greethy.nutritioncommon.entity.enums.NutritionType;
import com.greethy.nutritioncommon.entity.value.FoodMenu;
import com.greethy.nutritioncommon.entity.value.MealMenu;
import com.greethy.nutritioncommon.exception.NotFoundException;
import com.greethy.nutritionquery.domain.port.BodySpecPort;
import com.greethy.nutritionquery.domain.port.FoodPort;
import com.greethy.nutritionquery.domain.port.GorseClientPort;
import com.greethy.nutritionquery.domain.port.MenuPort;
import com.greethy.nutritionquery.domain.service.MenuQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuQueryServiceImpl implements MenuQueryService {

    private final MenuPort menuPort;

    private final FoodPort foodPort;

    private final ModelMapper mapper;

    private final Translator translator;

    private final BodySpecPort bodySpecPort;

    private final GorseClientPort gorseClientPort;

    @Override
    public Mono<MenuResponse> getMenuById(GetByIdQuery query) {
        return menuPort.findById(query.id())
                .switchIfEmpty(Mono.error(() -> {
                    var message = translator.getLocalizedMessage(Constants.MessageKeys.MENU_NOT_FOUND);
                    return new NotFoundException(message);
                })).map(menu -> mapper.map(menu, MenuResponse.class));
    }

    @Override
    public Mono<MenuResponse> suggestMenuFoods(String username) {
        return Flux.from(bodySpecPort.findFirstByUsernameOrOrderByCreatedAtDesc(username))
                .switchIfEmpty(Mono.error(this::bodySpecNotFound))
                .map(bodySpec -> NutritionType.valueOf(bodySpec.getGoal().toUpperCase(Locale.ROOT)))
                .flatMap(nutritionType -> {
                    var breakfastFoods = getFoodsForMenu(nutritionType.getBreakfastGroup())
                            .collectList()
                            .map(foodMenus -> MealMenu.builder().meal("breakfast").foods(foodMenus).build());
                    var lunchFoods = getFoodsForMenu(nutritionType.getLunchGroup())
                            .collectList()
                            .map(foodMenus -> MealMenu.builder().meal("lunch").foods(foodMenus).build());
                    var dinnerFoods = getFoodsForMenu(nutritionType.getDinnerGroup())
                            .collectList()
                            .map(foodMenus -> MealMenu.builder().meal("dinner").foods(foodMenus).build());
                    var snackFoods = getFoodsForMenu(nutritionType.getSnackGroup())
                            .collectList()
                            .map(foodMenus -> MealMenu.builder().meal("snack").foods(foodMenus).build());
                    return Flux.merge(breakfastFoods, lunchFoods, dinnerFoods, snackFoods);
                })
                .collectList()
                .map(mealMenus -> Menu.builder()
                        .meals(mealMenus)
                        .build())
                .doOnSuccess(menu -> log.info("Menu for User {} has been created: {}", username, menu))
                .map(menu -> mapper.map(menu, MenuResponse.class));
    }

    private Flux<FoodMenu> getFoodsForMenu(List<FoodGroup> groups) {
        var mutableGroups = new ArrayList<>(groups);
        return Flux.from(foodPort.findRandomByGroup(mutableGroups.get(0).getName()))
                .flatMap(firstFood -> Flux.fromIterable(mutableGroups)
                        .flatMap(group -> gorseClientPort.getSimilarityItems(firstFood.getId(), group.getName(), 1))
                ).map(GorseSimilarityResponse::getId)
                .flatMap(foodPort::findById)
                .map(food -> FoodMenu.builder()
                        .foodId(food.getId())
                        .name(food.getName())
                        .imageUrl(food.getImageUrls())
                        .calories(food.getTotalCalories())
                        .build());
    }

    private NotFoundException bodySpecNotFound() {
        var message = translator.getLocalizedMessage(Constants.MessageKeys.BODY_SPEC_NOT_FOUND);
        return new NotFoundException(message);
    }


}
