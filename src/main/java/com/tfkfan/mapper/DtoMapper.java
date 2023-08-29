package com.tfkfan.mapper;

import com.tfkfan.domain.Category;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Baltser Artem tfkfan
 */
public interface DtoMapper<E,D> extends BaseMapper{
    String DTO_MAPPING = "DTO_MAPPING";

    @Named(DTO_MAPPING)
    D toDto(E entity);

    default List<D> toDtos(List<E> entities){
        if(Objects.isNull(entities))
            return null;
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
