package com.ashish.graphqldemo.graphql.error;

import com.ashish.graphqldemo.graphql.enums.ErrorCode;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class UserDeletionFailedException extends GraphqlErrorWrapper {

    public UserDeletionFailedException(ErrorCode errorCode) {
        super(Collections.emptyMap(), errorCode);
    }
    public UserDeletionFailedException(ErrorCode errorCode, Map<String, Object> errorExtension) {
        super(errorExtension, errorCode);
    }
}
