package com.ashish.graphqldemo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "monthly_salary", nullable = false)
    private Integer monthlySalary;
    @Column(name = "monthly_expense", nullable = false)
    private Integer monthlyExpense;
}
