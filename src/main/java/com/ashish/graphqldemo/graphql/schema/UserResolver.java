package com.ashish.graphqldemo.graphql.schema;

import com.ashish.graphqldemo.graphql.input.CreateUserInput;
import com.ashish.graphqldemo.graphql.input.UpdateUserInput;
import com.ashish.graphqldemo.graphql.type.User;
import com.ashish.graphqldemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
public class UserResolver {
    @Autowired
    private UserService userService;

    @QueryMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @QueryMapping
    public User getUserById(@Argument Long id) {
        return userService.getUserById(id);
    }

    @MutationMapping
    @Transactional
    public User createUser(@Argument CreateUserInput input) {
        return userService.createUser(input);
    }

    @MutationMapping
    public User updateUser(@Argument UpdateUserInput input) {
        return userService.updateUser(input);
    }

    @MutationMapping
    @Transactional
    public Boolean deleteUser(@Argument Long id) {
        return userService.deleteUser(id);
    }
}
