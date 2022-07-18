package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Order;
import com.example.onlineshop.exceptionHandler.OrderNotFoundException;
import com.example.onlineshop.service.OrderService;
import com.example.onlineshop.dto.SaveOrderClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<Order> findAll() {
        return orderService.orderList();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id")int id){
        try {
            return orderService.findById(id);
        }catch (OrderNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found",e);
        }
    }
//    @PostMapping
//    public int save(@RequestBody SaveOrderRequest request) {
//        return orderService.save(request);
//    }

    @PostMapping
    public void saveOrderAndClient(@RequestBody SaveOrderClient saveOrderClient) {
        orderService.saveOrderAndClient(saveOrderClient);
    }
}
