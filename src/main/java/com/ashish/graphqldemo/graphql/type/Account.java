package com.ashish.graphqldemo.graphql.type;

import com.ashish.graphqldemo.graphql.enums.AccountType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Account {
    private Long accountId;

    private Long userId;

    private AccountType accountType;
}
