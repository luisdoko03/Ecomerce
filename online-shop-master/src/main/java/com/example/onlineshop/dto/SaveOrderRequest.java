package com.example.onlineshop.dto;

import lombok.Data;

@Data
public class SaveOrderRequest {
    private int id;
    private int finalPrice;
    private String comment;
    private int clientId;
    private int productId;
    private int quantity;
}
