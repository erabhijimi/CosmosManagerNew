package com.cosmos.controller;

import com.cosmos.model.Order;
import com.cosmos.pojo.Orders;
import com.cosmos.pojo.ResponseModel;
import com.cosmos.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    public ResponseModel saveOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }
    @GetMapping("/{mobileNumber}")
    public Orders getAllOrdersForSingleUser(@PathVariable Long mobileNumber){
    return orderService.getAllOrdersForSingleUser(mobileNumber);
    }
    @GetMapping
    public Orders getAllOrders(){
        return orderService.getAllOrders();
    }

}
