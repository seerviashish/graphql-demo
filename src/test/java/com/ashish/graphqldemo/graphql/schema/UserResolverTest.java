package com.ashish.graphqldemo.graphql.schema;

import com.ashish.graphqldemo.GraphqlDemoApplicationTests;
import com.ashish.graphqldemo.graphql.input.UpdateUserInput;
import com.ashish.graphqldemo.graphql.type.User;
import com.ashish.graphqldemo.repository.UserRepository;
import com.ashish.graphqldemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.when;

class UserResolverTest extends GraphqlDemoApplicationTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        when(userService.getAllUser()).thenReturn(mockUsers);
        when(userRepository.saveAndFlush(mockUserModels.get(0))).thenReturn(mockUserModels.get(0));
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUserModels.get(0)));
    }

    @Test
    void createUser_UserCreationSuccess() {
        graphqlTester.document(
                // language=GraphQL
                """
                        mutation CreateUser($createUserInput: CreateUserInput!) {
                          createUser(input:$createUserInput){
                            id
                            email
                            name
                            monthlySalary
                            monthlyExpense
                        \s
                          }
                        }
                        """).variable("createUserInput", mockCreateUserInputs.get(0)).execute().path("data.createUser").entity(User.class).equals(mockUsers.get(0));
    }

    @Test
    void createUser_UserCreationFailed() {
        graphqlTester.document(
                        // language=GraphQL
                        """
                                mutation CreateUser($createUserInput: CreateUserInput!) {
                                  createUser(input:$createUserInput){
                                    id
                                    email
                                    name
                                    monthlySalary
                                    monthlyExpense
                                  }
                                }
                                """).variable("createUserInput", mockCreateUserInputs.get(1))
                .execute().errors().expect(error -> error.getMessage().equals("User creation failed due to monthly salary - monthly expense < 1000")).verify();
    }

    @Test
    void updateUser_UserCreationSuccess() {
        User mockUser = mockUsers.get(0);
        mockUser.setName("Updated Name");
        UpdateUserInput mockUpdateUserInput = new UpdateUserInput(mockUser.getId(), mockUser.getName(), mockUser.getEmail(), mockUser.getMonthlySalary(), mockUser.getMonthlyExpense());
        com.ashish.graphqldemo.model.User mockUpdateUser = mockUserModels.get(2);
        mockUpdateUser.setName("Updated Name");
        when(userRepository.saveAndFlush(mockUpdateUser)).thenReturn(mockUpdateUser);
        graphqlTester.document(
                // language=GraphQL
                """
                        mutation UpdateUser($updateUserInput: UpdateUserInput!) {
                          updateUser(input:$updateUserInput){
                            id
                            email
                            name
                            monthlySalary
                            monthlyExpense
                          }
                        }
                        """).variable("updateUserInput", mockUpdateUserInput).execute().path("data.updateUser").entity(User.class).equals(mockUser);
    }

    @Test
    void updateUser_UserUpdationFailed() {
        User mockUser = mockUsers.get(0);
        UpdateUserInput mockUpdateUserInput = new UpdateUserInput(mockUser.getId(), mockUser.getName(), mockUser.getEmail(), mockUser.getMonthlySalary(), 20000F);
        graphqlTester.document(
                        // language=GraphQL
                        """
                                mutation UpdateUser($updateUserInput: UpdateUserInput!) {
                                  updateUser(input:$updateUserInput){
                                    id
                                    email
                                    name
                                    monthlySalary
                                    monthlyExpense
                                  }
                                }
                                        """).variable("updateUserInput", mockUpdateUserInput)
                .execute().errors().expect(error -> error.getMessage().equals("User updation failed due to monthly salary - monthly expense < 1000")).verify();
    }


    @Test
    void getUserById_Success() {
        graphqlTester.document(
                        // language=GraphQL
                        """
                                    query GetUserById($userId: ID!) {
                                      getUserById(id: $userId) {
                                        id
                                        email
                                        name
                                        monthlySalary
                                        monthlyExpense
                                      }
                                    }
                                """)
                .variable("userId", 1)
                .execute().path("data.getUserById").entity(User.class).equals(mockUsers.get(0));
    }

    @Test
    void getUserById_Failed() {
        graphqlTester.document(
                        // language=GraphQL
                        """
                                    query GetUserById($userId: ID!) {
                                      getUserById(id: $userId) {
                                        id
                                        email
                                        name
                                        monthlySalary
                                        monthlyExpense
                                      }
                                    }
                                """)
                .variable("userId", 2)
                .execute().errors().expect(error -> error.getMessage().equals(
                        "User is not found by given id")).verify();
    }

    @Test
    void getAllUser() {
        graphqlTester.document(
                // language=GraphQL
                """
                            query GetAllUser {
                              getAllUser {
                                id
                                email
                                name
                                monthlySalary
                                monthlyExpense
                              }
                            }
                        """).execute().path("data.getAllUser").entityList(User.class).equals(mockUsers);
    }
}