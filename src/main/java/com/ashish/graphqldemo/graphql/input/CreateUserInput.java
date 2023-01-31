package com.ashish.graphqldemo.graphql.input;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateUserInput {
    private String name;

    private String email;

    private Integer monthlySalary;

    private Integer monthlyExpense;
}
