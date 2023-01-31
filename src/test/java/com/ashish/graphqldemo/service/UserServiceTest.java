package com.ashish.graphqldemo.service;

import com.ashish.graphqldemo.GraphqlDemoApplicationTests;
import com.ashish.graphqldemo.graphql.input.UpdateUserInput;
import com.ashish.graphqldemo.graphql.type.User;
import com.ashish.graphqldemo.repository.AccountRepository;
import com.ashish.graphqldemo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceTest extends GraphqlDemoApplicationTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void init() {
        when(userRepository.findAll()).thenReturn(mockUserModels);
        when(userRepository.saveAndFlush(mockUserModels.get(0))).thenReturn(mockUserModels.get(0));
        when(accountRepository.saveAndFlush(mockAccountModels.get(0))).thenReturn(mockAccountModels.get(0));
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUserModels.get(0)));
    }

    @Test
    void getAllUser() {
        assertEquals(userService.getAllUser().size(), mockUsers.size());
    }

    @Test
    void getUserById() {
        assertEquals(userService.getUserById(1l), mockUsers.get(0));
    }

    @Test
    void createUser() {

        assertEquals(userService.createUser(mockCreateUserInputs.get(0)), new User(null, "Test One", "t1@g.com", 2000, 100));
    }

    @Test
    void updateUser() {
        User mockUser = mockUsers.get(0);
        mockUser.setName("Updated Name");
        UpdateUserInput mockUpdateUserInput = new UpdateUserInput(mockUser.getId(), mockUser.getName(), mockUser.getEmail(), mockUser.getMonthlySalary(), mockUser.getMonthlyExpense());
        com.ashish.graphqldemo.model.User mockUpdateUser = mockUserModels.get(0);
        mockUpdateUser.setName("Updated Name");
        assertEquals(userService.updateUser(mockUpdateUserInput), mockUser);
    }

    @Disabled
    @Test
    void deleteUser() {
    }
}