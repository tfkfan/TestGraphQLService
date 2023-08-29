package com.tfkfan.graphql;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author Baltser Artem tfkfan
 */
@Component
public class ModelResultDelegateImpl implements DataFetchersDelegateModelResult {
    @Override
    public Model model(DataFetchingEnvironment dataFetchingEnvironment, ModelResult origin) {
        return origin.getModel();
    }
}
