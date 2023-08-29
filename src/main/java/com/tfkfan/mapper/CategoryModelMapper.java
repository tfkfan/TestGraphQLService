package com.tfkfan.mapper;

import com.tfkfan.domain.Category;
import com.tfkfan.domain.CategoryProductModel;
import com.tfkfan.domain.ProductModel;
import com.tfkfan.graphql.CategoryModelResult;
import com.tfkfan.graphql.CategoryModelsResult;
import com.tfkfan.shared.PageUtil;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Baltser Artem tfkfan
 */
@Mapper(componentModel = "spring")
public abstract class CategoryModelMapper implements BaseMapper {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductModelMapper modelMapper;

    public CategoryModelResult toResponse(CategoryProductModel entity) {
        CategoryModelResult response = new CategoryModelResult();
        response.setCategory(categoryMapper.toDto(entity.getCategory()));
        response.setModel(modelMapper.toDto(entity.getModel()));

        return response;
    }

    public CategoryModelsResult toFindModelsByCategoryCodeResponse(Category category, Page<ProductModel> page) {
        CategoryModelsResult resp = new CategoryModelsResult();
        resp.setCategory(categoryMapper.toDto(category));
        resp.setPageInfo(PageUtil.pageInfo(page));
        resp.setModels(modelMapper.toDtos(page.getContent()));

        return resp;
    }
}
