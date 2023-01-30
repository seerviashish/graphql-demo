package com.ashish.graphqldemo.graphql.schema;

import com.ashish.graphqldemo.graphql.type.Account;
import com.ashish.graphqldemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AccountResolver {

    @Autowired
    private AccountService accountService;

    @QueryMapping
    public Account getAccountById(@Argument Long id) {
        return accountService.getAccountById(id);
    }

    @QueryMapping
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }
}
