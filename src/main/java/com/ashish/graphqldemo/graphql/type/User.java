package com.ashish.graphqldemo.graphql.type;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private Long id;
    private String name;
    private String email;
    private Integer monthlySalary;
    private Integer monthlyExpense;
}
