package com.greethy.nutritioncommand.domain.service.impl;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.nutritioncommand.domain.port.FoodPort;
import com.greethy.nutritioncommand.domain.service.FoodCommandService;
import com.greethy.nutritioncommon.dto.request.command.CreateFoodCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodCommandServiceImpl implements FoodCommandService {

    private final FoodPort foodPort;

    @Override
    public Mono<ObjectIdResponse> createFood(CreateFoodCommand request) {
        return null;
    }
}
