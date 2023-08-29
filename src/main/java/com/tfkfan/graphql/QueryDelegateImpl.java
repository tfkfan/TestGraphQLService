package com.tfkfan.graphql;

import com.tfkfan.mapper.CategoryMapper;
import com.tfkfan.mapper.ProductModelMapper;
import com.tfkfan.service.CategoryModelService;
import com.tfkfan.service.CategoryService;
import com.tfkfan.service.ProductModelService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Baltser Artem tfkfan
 */
@Component
public class QueryDelegateImpl implements DataFetchersDelegateQuery {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final ProductModelService modelService;
    private final ProductModelMapper modelMapper;
    private final CategoryModelService categoryModelService;

    public QueryDelegateImpl(CategoryService categoryService, CategoryMapper categoryMapper, ProductModelService modelService, ProductModelMapper modelMapper, CategoryModelService categoryModelService) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.modelService = modelService;
        this.modelMapper = modelMapper;
        this.categoryModelService = categoryModelService;
    }

    @Override
    public CategoryListResult findCategories(DataFetchingEnvironment dataFetchingEnvironment, List<String> codes, String name, String description, List<String> parentCategoryCodes, Boolean isHidden, Boolean onlyParent, PageSettings pageSettings) {
        return categoryMapper.toResponse(categoryService.findAll(codes,name, description, parentCategoryCodes, isHidden, onlyParent, pageSettings));
    }

    @Override
    public ModelListResult findModels(DataFetchingEnvironment dataFetchingEnvironment, List<String> codes, String name, String description, PageSettings pageSettings) {
        return modelMapper.toResponse(modelService.findAll(codes, name, description, pageSettings));
    }

    @Override
    public CategoryModelsResult findModelsByCategoryCode(DataFetchingEnvironment dataFetchingEnvironment, String categoryCode, PageSettings pageSettings) {
        return categoryModelService.findModelsByCategoryCode(categoryCode, pageSettings);
    }
}
