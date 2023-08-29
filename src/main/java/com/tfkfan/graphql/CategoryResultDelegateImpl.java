package com.tfkfan.graphql;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author Baltser Artem tfkfan
 */
@Component
public class CategoryResultDelegateImpl implements DataFetchersDelegateCategoryResult {
    @Override
    public Category category(DataFetchingEnvironment dataFetchingEnvironment, CategoryResult origin) {
        return origin.getCategory();
    }
}
