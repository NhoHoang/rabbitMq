package com.example.userservice.mapper;

import java.util.List;

/**
 * Generic Mapper uses for mapping between DTO and Entity.
 * If you want to use custom mapping, you can extend this class and define your own mapping.
 *
 * @param <D> DTO extends AbstractDTO
 * @param <E> Entity extends AbstractEntity
 */
public interface GenericMapper<D, E> {

    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDtoList(List<E> entities);

    List<E> toEntityList(List<D> dtos);

}

