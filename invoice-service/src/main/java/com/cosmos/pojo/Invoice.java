package com.cosmos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    private Address address;
    private Long contactNumber;
    private Set<Item> items;
}
