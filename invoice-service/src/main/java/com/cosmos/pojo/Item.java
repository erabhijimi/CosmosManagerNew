package com.cosmos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Long itemId;
    private int productId;
    private int quantityOfProduct;
    private Product product;
}
