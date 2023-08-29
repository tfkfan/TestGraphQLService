package com.tfkfan.graphql;

import com.tfkfan.exception.BusinessException;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.execution.*;
import graphql.language.SourceLocation;
import graphql.schema.GraphQLSchema;
import graphql.util.LogKit;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

/**
 * @author Baltser Artem tfkfan
 */
@Configuration
public class GraphqlConfiguration {

    @Bean
    @Primary
    public ExecutionStrategy strategy() {
        return new BusinessExecutionStrategy();
    }

    @Bean
    @Primary
    public GraphQL graphQL(GraphQLSchema graphQLSchema) throws IOException {
        ExecutionStrategy strategy = strategy();
        return GraphQL
            .newGraphQL(graphQLSchema)
            .mutationExecutionStrategy(strategy)
            .queryExecutionStrategy(strategy)
            .build();
    }

    static class BusinessExecutionStrategy extends AsyncSerialExecutionStrategy {
        public BusinessExecutionStrategy() {
            super(new GraphQLExceptionHandler());
        }
    }

    static class GraphQLExceptionHandler extends SimpleDataFetcherExceptionHandler {
        private static final Logger logNotSafe = LogKit.getNotPrivacySafeLogger(SimpleDataFetcherExceptionHandler.class);

        @Override
        public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {
            Throwable exception = this.unwrap(handlerParameters.getException());
            SourceLocation sourceLocation = handlerParameters.getSourceLocation();
            ResultPath path = handlerParameters.getPath();
            GraphQLError error = exception instanceof BusinessException ? (GraphQLError) exception :
                new ExceptionWhileDataFetching(path, exception, sourceLocation);
            logNotSafe.warn(error.getMessage(), exception);
            return DataFetcherExceptionHandlerResult.newResult().error(error).build();
        }
    }
}
