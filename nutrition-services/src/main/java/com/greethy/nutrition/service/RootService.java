package com.greethy.nutrition.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * An Interface representing for base services with abstract methods
 * for performing CRUD (Create, Read, Update, Delete) operations on data.
 *
 * @author ThanhKien
 * @param <T> The type of objects managed by this service.
 * @param <I> The type of object's ID
 * */
interface RootService<T, I> {

    /**
     * Insert a new specified entity to collection in connected MongoDB
     *
     * @param entity the specified entity to insert
     * */
    Mono<T> create(T entity);

    Mono<T> findOne(I id);

    Flux<T> findAll();

    Mono<T> update(T entity, I id);

    Mono<Void> deleteById(I id);

}
