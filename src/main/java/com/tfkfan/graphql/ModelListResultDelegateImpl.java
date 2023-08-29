package com.tfkfan.graphql;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Baltser Artem tfkfan
 */
@Component
public class ModelListResultDelegateImpl implements DataFetchersDelegateModelListResult {
    @Override
    public List<Model> models(DataFetchingEnvironment dataFetchingEnvironment, ModelListResult origin) {
        return origin.getModels();
    }

    @Override
    public PageInfo pageInfo(DataFetchingEnvironment dataFetchingEnvironment, ModelListResult origin) {
        return origin.getPageInfo();
    }
}
