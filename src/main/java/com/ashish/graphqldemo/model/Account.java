package com.ashish.graphqldemo.model;

import com.ashish.graphqldemo.graphql.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account_table")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Account {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
