package com.ashish.graphqldemo.graphql.error;

import com.ashish.graphqldemo.graphql.enums.ErrorCode;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Slf4j
public class GraphqlErrorWrapper extends RuntimeException implements GraphQLError {

    private static final String ERROR_CODE = "code";
    private Map<String, Object> errorExtension;
    private ErrorCode errorCode;

    @Override
    public List<SourceLocation> getLocations() {
        List<SourceLocation> locations = new ArrayList<>();
        StackTraceElement[] stackTraceElements = this.getStackTrace();
        if (stackTraceElements.length > 0) {
            StackTraceElement stackTraceElement = stackTraceElements[0];
            locations.add(new SourceLocation(stackTraceElement.getLineNumber(), 0));
        }
        return locations;

    }

    @Override
    public ErrorClassification getErrorType() {
        return this.errorCode.getErrorType();
    }

    @Override
    public List<Object> getPath() {
        List<Object> path = new ArrayList<>();
        StackTraceElement[] stackTraceElements = this.getStackTrace();
        if (stackTraceElements.length > 0) {
            StackTraceElement stackTraceElement = stackTraceElements[0];
            String pathName = "Error occurred in class " +
                    stackTraceElement.getClassName() +
                    " [ FileName: " +
                    stackTraceElement.getFileName() +
                    " ] at methodName " + stackTraceElement.getMethodName();
            path.add(pathName);
        }
        return path;
    }

    @Override
    public Map<String, Object> toSpecification() {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> extensions = new HashMap<>(errorExtension);
        extensions.put(ERROR_CODE, errorCode.getCode());
        return extensions;
    }
}
