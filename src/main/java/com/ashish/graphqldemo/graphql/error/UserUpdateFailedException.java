package com.ashish.graphqldemo.graphql.error;

import com.ashish.graphqldemo.graphql.enums.ErrorCode;

import java.util.Collections;
import java.util.Map;

public class UserUpdateFailedException extends GraphqlErrorWrapper{
    public UserUpdateFailedException(ErrorCode errorCode, Map<String, Object> errorExtension) {
        super(errorExtension, errorCode);
    }

    public UserUpdateFailedException(ErrorCode errorCode) {
        super(Collections.emptyMap(), errorCode);
    }

}
