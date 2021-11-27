package com.cosmos.service;

import com.cosmos.model.Order;
import com.cosmos.pojo.Orders;
import com.cosmos.pojo.ResponseModel;
import com.cosmos.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    private String cartUrl="http://CARTS-SERVICE/cart/";

    public ResponseModel saveOrder(Order order) {
        order.setOrderTime(LocalDate.now());
        order.setStatus(true);
        orderRepository.save(order);
        //add logic to remove userCart from cart service
        restTemplate.delete(cartUrl+order.getMobileNumber());
        log.info("Order received: "+order.toString());
        ResponseModel responseModel=new ResponseModel();
        responseModel.setResponseCode(200);
        responseModel.setResponseMsg("Ordered Placed Successfully..");
        return  responseModel;
    }

    public Orders getAllOrdersForSingleUser(Long mobileNumber) {
        List<Order> orderList =orderRepository.findOrderByMobileNumber(mobileNumber);
        Orders orders = new Orders();
        orders.setOrders(orderList);
        return orders;
    }

    public Orders getAllOrders() {
        List<Order> orderList =orderRepository.findAll();
        Orders orders = new Orders();
        orders.setOrders(orderList);
        return orders;
    }
}
