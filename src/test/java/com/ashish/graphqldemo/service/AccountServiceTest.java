package com.ashish.graphqldemo.service;

import com.ashish.graphqldemo.GraphqlDemoApplicationTests;
import com.ashish.graphqldemo.repository.AccountRepository;
import com.ashish.graphqldemo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceTest extends GraphqlDemoApplicationTests {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;


    @BeforeEach
    public void init() {
        when(accountRepository.findAll()).thenReturn(mockAccountModels);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(mockAccountModels.get(0)));
    }

    @Test
    void getAccountById() {
        assertEquals(accountService.getAccountById(1l), mockAccounts.get(0));
    }

    @Test
    void getAllAccount() {
        assertEquals(accountService.getAllAccount().size(), mockAccounts.size());
        assertEquals(accountService.getAllAccount(), mockAccounts);
    }
}