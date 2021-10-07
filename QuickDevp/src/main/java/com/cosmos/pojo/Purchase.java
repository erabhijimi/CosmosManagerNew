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
public class Purchase {
        private Long custId;
        private Address custAddress;
        private LocalDate saleDate;
        private Product product;
        private Inventory inventoryCreate;

}
