package com.ashish.graphqldemo.service;


import com.ashish.graphqldemo.graphql.enums.ErrorCode;
import com.ashish.graphqldemo.graphql.error.AccountNotFoundException;
import com.ashish.graphqldemo.graphql.type.Account;
import com.ashish.graphqldemo.graphql.type.PagedResult;
import com.ashish.graphqldemo.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    public Account getAccountById(Long id) {
        com.ashish.graphqldemo.model.Account account = accountRepository.findById(id).orElseThrow(() -> {
            log.error("Error in AccountService for function getAccountById -> id =>  {} , User Not Found", id);
            throw new AccountNotFoundException(ErrorCode.AccountNotFound_005);
        });
        Account accountResponse = new Account();
        accountResponse.setAccountId(account.getId());
        accountResponse.setUserId(account.getUser().getId());
        accountResponse.setAccountType(account.getAccountType());
        return accountResponse;
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll().stream().map(account -> {
            Account accountResponse = new Account();
            accountResponse.setAccountId(account.getId());
            accountResponse.setAccountType(account.getAccountType());
            accountResponse.setUserId(account.getUser().getId());
            return accountResponse;
        }).collect(Collectors.toList());
    }

    public PagedResult<Account> getAllAccountPaged(Pageable pageable) {
        PagedResult<Account> pagedResult = new PagedResult();
        long total = accountRepository.count();
        List<Account> accounts = accountRepository.findAll(pageable).stream().map(account -> {
            Account accountResponse = new Account();
            accountResponse.setAccountId(account.getId());
            accountResponse.setAccountType(account.getAccountType());
            accountResponse.setUserId(account.getUser().getId());
            return accountResponse;
        }).collect(Collectors.toList());
        pagedResult.setItems(accounts);
        pagedResult.setTotal(total);
        return pagedResult;
    }

}
