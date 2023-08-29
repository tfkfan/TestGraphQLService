package com.tfkfan.mapper;

import com.tfkfan.domain.ProductModel;
import com.tfkfan.graphql.Model;
import com.tfkfan.graphql.ModelListResult;
import com.tfkfan.graphql.ModelResult;
import com.tfkfan.shared.PageUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Baltser Artem tfkfan
 */
@Mapper(componentModel = "spring")
public interface ProductModelMapper extends DtoMapper<ProductModel, Model>{
    @Named(DTO_MAPPING)
    @Mapping(target = "withCode", source = "entity.code")
    @Mapping(target = "withName", source = "entity.name")
    @Mapping(target = "withDescription", source = "entity.description")
    Model toDto(ProductModel entity);

    @Mapping(target = "withModel", source = "entity", qualifiedByName = DTO_MAPPING)
    ModelResult toResponse(ProductModel entity);

    default ModelListResult toResponse(Page<ProductModel> page) {
        ModelListResult resp = new ModelListResult();

        resp.setModels(toDtos(page.getContent()));
        resp.setPageInfo(PageUtil.pageInfo(page));
        return resp;
    }
}
