package com.ashish.graphqldemo.service;

import com.ashish.graphqldemo.graphql.enums.AccountType;
import com.ashish.graphqldemo.graphql.enums.ErrorCode;
import com.ashish.graphqldemo.graphql.error.*;
import com.ashish.graphqldemo.graphql.input.CreateUserInput;
import com.ashish.graphqldemo.graphql.input.UpdateUserInput;
import com.ashish.graphqldemo.graphql.type.PagedResult;
import com.ashish.graphqldemo.graphql.type.User;
import com.ashish.graphqldemo.model.Account;
import com.ashish.graphqldemo.repository.AccountRepository;
import com.ashish.graphqldemo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<User> getAllUser() {
        return userRepository.findAll().stream().map(user -> {
            User userResponse = new User();
            userResponse.setId(user.getId());
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            userResponse.setMonthlySalary(user.getMonthlySalary());
            userResponse.setMonthlyExpense(user.getMonthlyExpense());
            return userResponse;
        }).collect(Collectors.toList());
    }
    public PagedResult<User> getAllUserPaged(Pageable pageable) {
        PagedResult<User> pagedResult = new PagedResult<>();
        long total = userRepository.count();

        List<User> users =  userRepository.findAll(pageable).stream().map(user -> {
            User userResponse = new User();
            userResponse.setId(user.getId());
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            userResponse.setMonthlySalary(user.getMonthlySalary());
            userResponse.setMonthlyExpense(user.getMonthlyExpense());
            return userResponse;
        }).collect(Collectors.toList());
        pagedResult.setItems(users);
        pagedResult.setTotal(total);
        return pagedResult;
    }


    public User getUserById(Long id) {
        com.ashish.graphqldemo.model.User user = userRepository.findById(id).orElseThrow(() -> {
            log.error("Error in user service for function getUserById -> id =>  {} , User Not Found", id);
            throw new UserNotFoundException(ErrorCode.UserNotFound_003);
        });
        User userResponse = new User();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setMonthlySalary(user.getMonthlySalary());
        userResponse.setMonthlyExpense(user.getMonthlyExpense());
        return userResponse;
    }

    public User createUser(CreateUserInput input) {
        if (input.getMonthlySalary() - input.getMonthlyExpense() < 1000) {
            throw new UserCreationFailedException(ErrorCode.UserCreationFailed_001);
        }
        try {
            com.ashish.graphqldemo.model.User user = new com.ashish.graphqldemo.model.User();
            user.setName(input.getName());
            user.setEmail(input.getEmail());
            user.setMonthlySalary(input.getMonthlySalary());
            user.setMonthlyExpense(input.getMonthlyExpense());
            userRepository.saveAndFlush(user);
            Account account = new Account();
            account.setUser(user);
            account.setAccountType(AccountType.DEBIT);
            accountRepository.saveAndFlush(account);
            User userResponse = new User();
            userResponse.setId(user.getId());
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            userResponse.setMonthlySalary(user.getMonthlySalary());
            userResponse.setMonthlyExpense(user.getMonthlyExpense());
            return userResponse;
        } catch (Exception e) {
            log.error("Error in user service for function createUser -> input =>  {} , error => {}", input, e);
            throw new UserCreationFailedException(ErrorCode.UserCreationFailed_002);
        }
    }

    public User updateUser(UpdateUserInput input) {
        if (input.getMonthlySalary() - input.getMonthlyExpense() < 1000) {
            throw new UserCreationFailedException(ErrorCode.UserUpdateFailed_006);
        }
        try {
            com.ashish.graphqldemo.model.User user = new com.ashish.graphqldemo.model.User();
            user.setId(input.getId());
            user.setName(input.getName());
            user.setEmail(input.getEmail());
            user.setMonthlySalary(input.getMonthlySalary());
            user.setMonthlyExpense(input.getMonthlyExpense());
            userRepository.saveAndFlush(user);
            User userResponse = new User();
            userResponse.setId(user.getId());
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            userResponse.setMonthlySalary(user.getMonthlySalary());
            userResponse.setMonthlyExpense(user.getMonthlyExpense());
            return userResponse;
        } catch (Exception e) {
            log.error("Error in user service for function updateUser -> input =>  {} , error => {}", input, e);
            throw new UserUpdateFailedException(ErrorCode.UserUpdateFailed_008);
        }

    }

    public Boolean deleteUser(Long id) {
        try {
            boolean isAccountExist = accountRepository.existsById(id);
            if (!isAccountExist) {
                throw new AccountNotFoundException(ErrorCode.AccountNotFound_005);
            }
            accountRepository.deleteByUserId(id);
            return true;
        } catch (Exception e) {
            log.error("Error in user service for function deleteUser -> id =>  {} , error => {}", id, e);
            if (e instanceof AccountNotFoundException) {
                throw e;
            }
            throw new UserDeletionFailedException(ErrorCode.UserAndAccountDeletionFailed_007);
        }
    }
}
