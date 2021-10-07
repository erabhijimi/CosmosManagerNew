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
public class Inventory {
    private int quantityOfProduct;
    private Long productId;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;
    private LocalDate mfgDate;
    private String bestBeforeDays;
    private String comments;
}
