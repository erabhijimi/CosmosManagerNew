package com.cosmos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCart {
	private Long mobileNumber;
	private Set<Item> items;
}
