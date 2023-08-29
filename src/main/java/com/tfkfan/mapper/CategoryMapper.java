package com.tfkfan.mapper;

import com.tfkfan.domain.Category;
import com.tfkfan.domain.ProductModel;
import com.tfkfan.graphql.CategoryListResult;
import com.tfkfan.graphql.CategoryResult;
import com.tfkfan.graphql.Model;
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
public interface CategoryMapper extends DtoMapper<Category, com.tfkfan.graphql.Category> {

    @Named(DTO_MAPPING)
    @Mapping(target = "withCode", source = "entity.code")
    @Mapping(target = "withName", source = "entity.name")
    @Mapping(target = "withDescription", source = "entity.description")
    @Mapping(target = "withIsHidden", source = "entity.isHidden")
    @Mapping(target = "withParentCategory", source = "entity.parentCategory", qualifiedByName = DTO_MAPPING)
    com.tfkfan.graphql.Category toDto(Category entity);


    @Mapping(target = "withCategory", source = "entity", qualifiedByName = DTO_MAPPING)
    CategoryResult toResponse(Category entity);

    @Mapping(target = "category", source = "entity", qualifiedByName = DTO_MAPPING)
    default CategoryListResult toResponse(Page<Category> page) {
        CategoryListResult resp = new CategoryListResult();

        resp.setCategories(toDtos(page.getContent()));
        resp.setPageInfo(PageUtil.pageInfo(page));
        return resp;
    }
}
