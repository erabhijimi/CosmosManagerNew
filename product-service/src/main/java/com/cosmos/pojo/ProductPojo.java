package com.cosmos.pojo;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPojo {
    @CsvBindByPosition(position = 2)
    private String productType;
    @CsvBindByPosition(position = 1)
    private String productName;
    @CsvBindByPosition(position = 3)
    private String productAmount;
    @CsvBindByPosition(position = 4)
    private String productMRP;
    @CsvBindByPosition(position = 5)
    private String productRealPrice;
    @CsvBindByPosition(position = 6)
    private String productSellingPrice;
    @CsvBindByPosition(position = 7)
    private String productInStore;
    @CsvBindByPosition(position = 8)
    private String productPurchaseDate;
    @CsvBindByPosition(position = 9)
    private String productExpireDate;
    @CsvBindByPosition(position = 10)
    private String additionalComment;
}
