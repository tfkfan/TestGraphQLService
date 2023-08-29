package com.tfkfan.service;


import com.tfkfan.graphql.CategoryModelResult;
import com.tfkfan.graphql.CategoryModelsResult;
import com.tfkfan.graphql.PageSettings;

/**
 * @author Baltser Artem tfkfan
 */
public interface CategoryModelService {
    CategoryModelResult linkCategoryToModel(String categoryCode, String modelCode);

    CategoryModelResult unlinkCategoryToModel(String categoryCode, String modelCode);

    CategoryModelsResult findModelsByCategoryCode(String categoryCode, PageSettings pageSettings);
}
