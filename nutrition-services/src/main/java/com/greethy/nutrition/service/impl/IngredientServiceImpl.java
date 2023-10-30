package com.greethy.nutrition.service.impl;

import com.greethy.nutrition.entity.Ingredient;
import com.greethy.nutrition.repository.IngredientRepository;
import com.greethy.nutrition.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author ThanhKien
 * */
@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Mono<Ingredient> create(Ingredient entity) {
        return ingredientRepository.save(entity);
    }

    @Override
    public Mono<Ingredient> findOne(String id) {
        return null;
    }

    @Override
    public Flux<Ingredient> findAll() {
        return null;
    }

    @Override
    public Mono<Ingredient> update(Ingredient entity, String id) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return ingredientRepository.deleteById(id);
    }


}
