package com.ashish.graphqldemo.graphql.type;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PagedResult<T> {
    private long total;
    private List<T> items;
}
