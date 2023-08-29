package com.tfkfan.graphql;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Baltser Artem tfkfan
 */
@Component
public class CategoryListResultDelegateImpl implements DataFetchersDelegateCategoryListResult {
    @Override
    public List<Category> categories(DataFetchingEnvironment dataFetchingEnvironment, CategoryListResult origin) {
        return origin.getCategories();
    }

    @Override
    public PageInfo pageInfo(DataFetchingEnvironment dataFetchingEnvironment, CategoryListResult origin) {
        return origin.getPageInfo();
    }
}
