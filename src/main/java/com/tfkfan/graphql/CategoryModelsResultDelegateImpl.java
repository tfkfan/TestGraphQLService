package com.tfkfan.graphql;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Baltser Artem tfkfan
 */
@Component
public class CategoryModelsResultDelegateImpl implements DataFetchersDelegateCategoryModelsResult {
    @Override
    public Category category(DataFetchingEnvironment dataFetchingEnvironment, CategoryModelsResult origin) {
        return origin.getCategory();
    }

    @Override
    public List<Model> models(DataFetchingEnvironment dataFetchingEnvironment, CategoryModelsResult origin) {
        return origin.getModels();
    }

    @Override
    public PageInfo pageInfo(DataFetchingEnvironment dataFetchingEnvironment, CategoryModelsResult origin) {
        return origin.getPageInfo();
    }
}
