package com.ashish.graphqldemo;

import com.ashish.graphqldemo.graphql.enums.AccountType;
import com.ashish.graphqldemo.graphql.input.CreateUserInput;
import com.ashish.graphqldemo.graphql.type.Account;
import com.ashish.graphqldemo.graphql.type.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.WebGraphQlTester;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureHttpGraphQlTester
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GraphqlDemoApplicationTests {

    protected static final List<User> mockUsers = Arrays.asList(
            new User(1L, "Test One", "t1@g.com", 2000, 100),
            new User(2L, "Test Two", "t2@g.com", 3000, 200),
            new User(3L, "Test Three", "t3@g.com", 4000, 300),
            new User(4L, "Test Four", "t4@g.com", 5000, 400),
            new User(5L, "Test Five", "t5@g.com", 6000, 500));

    protected static final List<Account> mockAccounts = Arrays.asList(
            new Account(1L, 1L, AccountType.DEBIT),
            new Account(2L, 2L, AccountType.DEBIT),
            new Account(3L, 3L, AccountType.DEBIT),
            new Account(4L, 4L, AccountType.DEBIT),
            new Account(5L, 5L, AccountType.DEBIT)
    );

    protected static final List<com.ashish.graphqldemo.model.User> mockUserModels = Arrays.asList(
            new com.ashish.graphqldemo.model.User(1L, "Test One", "t1@g.com", 2000, 100),
            new com.ashish.graphqldemo.model.User(2L, "Test Two", "t2@g.com", 3000, 200),
            new com.ashish.graphqldemo.model.User(3L, "Test Three", "t3@g.com", 4000, 300),
            new com.ashish.graphqldemo.model.User(4L, "Test Four", "t4@g.com", 5000, 400),
            new com.ashish.graphqldemo.model.User(5L, "Test Five", "t5@g.com", 6000, 500));

    protected static final List<com.ashish.graphqldemo.model.Account> mockAccountModels = Arrays.asList(
            new com.ashish.graphqldemo.model.Account(1L, AccountType.DEBIT, mockUserModels.get(0)),
            new com.ashish.graphqldemo.model.Account(2L, AccountType.DEBIT, mockUserModels.get(1)),
            new com.ashish.graphqldemo.model.Account(3L, AccountType.DEBIT, mockUserModels.get(2)),
            new com.ashish.graphqldemo.model.Account(4L, AccountType.DEBIT, mockUserModels.get(3)),
            new com.ashish.graphqldemo.model.Account(5L, AccountType.DEBIT, mockUserModels.get(4))
    );

    protected static final List<CreateUserInput> mockCreateUserInputs = Arrays.asList(new CreateUserInput("Test One", "t1@g.com", 2000, 100), new CreateUserInput("Test Two", "t2@g.com", 300, 200));


    protected WebGraphQlTester graphqlTester;

    @BeforeEach
    void setupWebGraphqlTester(@Autowired WebGraphQlTester graphQlTester) {
        this.graphqlTester = graphQlTester;
    }
}
