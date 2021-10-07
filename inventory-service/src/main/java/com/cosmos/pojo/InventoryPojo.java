package com.cosmos.pojo;

import com.cosmos.model.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryPojo {
    private Inventory inventory;
    private ProductPojo product;
}
