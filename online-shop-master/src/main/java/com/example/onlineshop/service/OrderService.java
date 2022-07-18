package com.example.onlineshop.service;

import com.example.onlineshop.dto.SaveOrderClient;
import com.example.onlineshop.dto.SaveOrderRequest;
import com.example.onlineshop.entity.Order;
import com.example.onlineshop.exceptionHandler.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    List<Order> orderList();
    Order findById(int id) throws OrderNotFoundException;

    int save(SaveOrderRequest order);
    void saveOrderAndClient(SaveOrderClient orderClient);
    List<Order> findOrdersByClientId(int id);
}
