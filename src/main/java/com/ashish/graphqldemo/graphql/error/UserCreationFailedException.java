package com.ashish.graphqldemo.graphql.error;

import com.ashish.graphqldemo.graphql.enums.ErrorCode;

import java.util.Collections;
import java.util.Map;

public class UserCreationFailedException extends GraphqlErrorWrapper {

    public UserCreationFailedException(ErrorCode errorCode) {
        super(Collections.emptyMap(), errorCode);
    }

    public UserCreationFailedException(ErrorCode errorCode, Map<String, Object> errorExtension) {
        super(errorExtension, errorCode);
    }

}
