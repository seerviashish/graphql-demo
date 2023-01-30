package com.ashish.graphqldemo.graphql.enums;


import graphql.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    UserCreationFailed_001(1, "User creation failed due to monthly salary - monthly expense < 1000", ErrorType.OperationNotSupported),
    UserCreationFailed_002(2, "User insertion or update query failed", ErrorType.ExecutionAborted),

    UserNotFound_003(3, "User is not found by given id", ErrorType.DataFetchingException),

    AccountCreationFailed_004(4, "Account creation failed", ErrorType.ExecutionAborted),

    AccountNotFound_005(5, "Account not found by id", ErrorType.DataFetchingException),

    UserUpdateFailed_006(6, "User creation failed due to monthly salary - monthly expense < 1000", ErrorType.OperationNotSupported),

    UserAndAccountDeletionFailed_007(7, "Account and User delete query failed", ErrorType.ExecutionAborted),

    UserUpdateFailed_008(8, "User insertion or update query failed", ErrorType.ExecutionAborted);


    private int code;

    private String message;


    private ErrorType errorType;

}
