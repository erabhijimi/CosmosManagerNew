package com.cosmos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Long productId;
    private String productName;
    private String  productCategory;
    private String  productType;
    private String  productBrand;
    private String productUnit;
    private int productAmount;
    private int productMRP;
    private int productRealPrice;
    private int productSellingPrice;
    private int quantityOfProduct;
    private LocalDate mfgDate;
    private String bestBeforeDays;
    private String comments;
}
