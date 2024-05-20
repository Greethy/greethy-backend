package com.greethy.nutrition.infra.repository.redis.adapter;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutrition.core.domain.entity.Food;
import com.greethy.nutrition.core.port.out.FoodPort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@DrivenAdapter("redisFoodAdapter")
public class RedisFoodAdapter implements FoodPort {

    private static final String KEY = "food";

    private final ReactiveHashOperations<String, String, Food> hashOperations;

    public RedisFoodAdapter(ReactiveRedisOperations<String, Food> redisOperations) {
        this.hashOperations = redisOperations.opsForHash();
    }


    @Override
    public Mono<Food> save(Food food) {
        return hashOperations.put(KEY, food.getId(), food)
                .thenReturn(food);
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return hashOperations.hasKey(KEY, id);
    }

    @Override
    public Mono<Void> deleteAll() {
        return hashOperations.delete(KEY).then();
    }

    @Override
    public Mono<Long> countAll() {
        return hashOperations.values(KEY).count();
    }

    @Override
    public Mono<Food> findById(String id) {
        return hashOperations.get(KEY, id);
    }

    @Override
    public Flux<Food> saveAll(Iterable<Food> foods) {
        return Flux.fromIterable(foods)
                .flatMap(this::save);
    }

    @Override
    public Flux<Food> findAll() {
        return hashOperations.values(KEY);
    }

    @Override
    public Flux<Food> findAllBy(Pageable pageable) {
        return hashOperations.values(KEY);
    }
}
