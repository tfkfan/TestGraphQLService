package com.tfkfan.graphql;

import com.tfkfan.mapper.CategoryMapper;
import com.tfkfan.mapper.ProductModelMapper;
import com.tfkfan.service.CategoryModelService;
import com.tfkfan.service.CategoryService;
import com.tfkfan.service.ProductModelService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author Baltser Artem tfkfan
 */
@Component
public class MutationDelegateImpl implements DataFetchersDelegateMutation {
    private final CategoryService categoryService;
    private final ProductModelService modelService;
    private final CategoryModelService categoryModelService;
    private final CategoryMapper categoryMapper;
    private final ProductModelMapper modelMapper;

    public MutationDelegateImpl(CategoryService categoryService, ProductModelService modelService, CategoryModelService categoryModelService, CategoryMapper categoryMapper, ProductModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelService = modelService;
        this.categoryModelService = categoryModelService;
        this.categoryMapper = categoryMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResult createCategory(DataFetchingEnvironment dataFetchingEnvironment, String code, String name, String description, String parentCategoryCode) {
        return categoryMapper.toResponse(categoryService.create(code, name, description, parentCategoryCode));
    }

    @Override
    public CategoryResult updateCategory(DataFetchingEnvironment dataFetchingEnvironment, String code, String name, String description, Boolean isHidden, String parentCategoryCode) {
        return categoryMapper.toResponse(categoryService.update(code, name, description, isHidden, parentCategoryCode));
    }

    @Override
    public ModelResult createModel(DataFetchingEnvironment dataFetchingEnvironment, String code, String name, String description) {
        return modelMapper.toResponse(modelService.create(code, name, description));
    }

    @Override
    public ModelResult updateModel(DataFetchingEnvironment dataFetchingEnvironment, String code, String name, String description) {
        return modelMapper.toResponse(modelService.update(code, name, description));
    }

    @Override
    public CategoryModelResult linkCategoryToModel(DataFetchingEnvironment dataFetchingEnvironment, String categoryCode, String modelCode) {
        return categoryModelService.linkCategoryToModel(categoryCode, modelCode);
    }

    @Override
    public CategoryModelResult unlinkCategoryToModel(DataFetchingEnvironment dataFetchingEnvironment, String category_code, String model_code) {
        return categoryModelService.unlinkCategoryToModel(category_code, model_code);
    }
}
