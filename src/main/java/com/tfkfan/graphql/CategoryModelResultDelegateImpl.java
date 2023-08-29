package com.tfkfan.graphql;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author Baltser Artem tfkfan
 */
@Component
public class CategoryModelResultDelegateImpl implements DataFetchersDelegateCategoryModelResult {
    @Override
    public Category category(DataFetchingEnvironment dataFetchingEnvironment, CategoryModelResult origin) {
        return origin.getCategory();
    }

    @Override
    public Model model(DataFetchingEnvironment dataFetchingEnvironment, CategoryModelResult origin) {
        return origin.getModel();
    }
}
