package com.cosmos.pojo;

import com.cosmos.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Products {
	private List<Product> products;
}
