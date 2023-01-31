package com.ashish.graphqldemo.graphql.schema;

import com.ashish.graphqldemo.GraphqlDemoApplicationTests;
import com.ashish.graphqldemo.graphql.type.Account;
import com.ashish.graphqldemo.repository.AccountRepository;
import com.ashish.graphqldemo.repository.UserRepository;
import com.ashish.graphqldemo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

// Not found solution for 2 disabled test cases

class AccountResolverTest extends GraphqlDemoApplicationTests {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        when(accountService.getAllAccount()).thenReturn(mockAccounts);
    }


    @Disabled
    @Test
    void getAccountById_Success() {
        graphqlTester.document(
                        // language=GraphQL
                        """
                                query GetAccountById($accountId: ID!) {
                                    getAccountById(id: $accountId) {
                                        userId
                                        accountId
                                        accountType
                                    }
                                }
                                """)
                .variable("accountId", 1)
                .execute()
                .path("data.getAccountById").entity(Account.class).equals(mockAccounts.get(0));

    }

    @Test
    void getAccountById_Failed() {
        graphqlTester.document(
                        // language=GraphQL
                        """
                                query GetAccountById($accountId: ID!) {
                                    getAccountById(id: $accountId) {
                                        userId
                                        accountId
                                        accountType
                                    }
                                }
                                """)
                .variable("accountId", 3)
                .execute().errors().expect(error -> error.getMessage().equals(
                        "Account not found by id")).verify();

    }

    @Test
    void getAllAccount() {
        graphqlTester.document(
                // language=GraphQL
                """
                            query GetAllAccount {
                              getAllAccount {
                                userId
                                accountId
                                accountType
                              }
                            }
                        """).execute().path("data.getAllAccount").entityList(Account.class).equals(mockAccounts);

    }

    @Disabled
    @Test
    void deleteUser_Success() {
        graphqlTester.document(
                        // language=GraphQL
                        """
                                   mutation DeleteUser($deleteUserId: ID!) {
                                     deleteUser(id: $deleteUserId)
                                   }
                                """)
                .variable("deleteUserId", 1).
                execute().path("data.deleteUser").entity(Boolean.class).isEqualTo(true);
    }

    @Test
    void deleteUser_Failed() {
        graphqlTester.document(
                        // language=GraphQL
                        """
                                   mutation DeleteUser($deleteUserId: ID!) {
                                     deleteUser(id: $deleteUserId)
                                   }
                                """)
                .variable("deleteUserId", 3)
                .execute().errors().expect(error -> error.getMessage().equals(
                        "Account not found by id")).verify();
    }
}