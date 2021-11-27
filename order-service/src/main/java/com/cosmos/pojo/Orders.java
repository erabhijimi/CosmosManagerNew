package com.cosmos.pojo;

import com.cosmos.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private List<Order> orders;
}
