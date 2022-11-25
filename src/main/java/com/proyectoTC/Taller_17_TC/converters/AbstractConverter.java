package com.proyectoTC.Taller_17_TC.converters;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E, D> {


    public abstract E fromDTO(D dto);

    public abstract D fromEntity(E entity);

    public List<D> fromEntity(List<E> entities) {
        return entities.stream().map(this::fromEntity).collect(Collectors.toList());
    }

    public List<E> fromDTO(List<D> dtos) {
        return dtos.stream().map(this::fromDTO).collect(Collectors.toList());
    }

}
