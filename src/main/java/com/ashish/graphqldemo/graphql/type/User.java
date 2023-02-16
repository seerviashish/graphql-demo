package com.ashish.graphqldemo.graphql.type;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User implements PageData{
    private Long id;
    private String name;
    private String email;
    private Float monthlySalary;
    private Float monthlyExpense;
}
