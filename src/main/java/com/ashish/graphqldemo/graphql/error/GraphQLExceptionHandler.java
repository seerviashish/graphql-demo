package com.ashish.graphqldemo.graphql.error;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected List<GraphQLError> resolveToMultipleErrors(Throwable ex, DataFetchingEnvironment env) {
        return super.resolveToMultipleErrors(ex, env);
    }

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof GraphqlErrorWrapper) {
            List<SourceLocation> sourceLocations = ((GraphqlErrorWrapper) ex).getLocations();
            GraphqlErrorBuilder errorBuilder = GraphqlErrorBuilder
                    .newError()
                    .errorType(((GraphqlErrorWrapper) ex).getErrorType())
                    .message(((GraphqlErrorWrapper) ex).getErrorCode().getMessage())
                    .path(((GraphqlErrorWrapper) ex).getPath());
            if (sourceLocations.size() > 0) {
                errorBuilder.location(sourceLocations.get(0));
            }
            errorBuilder.extensions(((GraphqlErrorWrapper) ex).getExtensions());
            return errorBuilder.build();
        }
        return null;
    }
}
