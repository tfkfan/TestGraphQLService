package com.tfkfan.graphql;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author Baltser Artem tfkfan
 */
@Component
public class CategoryDelegateImpl implements DataFetchersDelegateCategory{
    @Override
    public Category parentCategory(DataFetchingEnvironment dataFetchingEnvironment, Category origin) {
        return origin.parentCategory;
    }
}
