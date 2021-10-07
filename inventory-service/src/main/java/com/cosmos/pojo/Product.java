package com.cosmos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private int productId;
	private String productName;
	private String productCategory;
	private String productType;
	private String productBrand;
	private String productUnit;
	private Float productAmount;
	private Float productMRP;
	private Float productRealPrice;
	private Float productSellingPrice;
	private boolean active;
	private LocalDate now;
	private boolean addedToCart;

}
