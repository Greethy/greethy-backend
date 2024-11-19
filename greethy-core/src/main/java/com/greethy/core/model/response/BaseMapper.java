package com.greethy.core.model.response;

/**
 * {@link BaseMapper} is a generic base interface for mapping between Domain's Entity and objects model.
 *
 * @param <E> The Entity type that represents Domain Entity.
 * @param <M> the Model type that represents the DTO or Business model.
 *
 * @author ThanhKien
 * @since 1.0.0
 * */
public interface BaseMapper<E, M> {

    E toEntity(M m);

    M toModel(E e);

}
