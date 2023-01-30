package com.ashish.graphqldemo.graphql.error;

import com.ashish.graphqldemo.graphql.enums.ErrorCode;

import java.util.Collections;
import java.util.Map;

public class AccountNotFoundException extends GraphqlErrorWrapper {

    public AccountNotFoundException(ErrorCode errorCode) {
        super(Collections.emptyMap(), errorCode);
    }

    public AccountNotFoundException(ErrorCode errorCode, Map<String, Object> errorExtension) {
        super(errorExtension, errorCode);
    }
}
