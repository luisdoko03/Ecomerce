package com.example.onlineshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveOrderClient {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int productId;
    private int quantity;
    private int finalPrice;

    public SaveOrderClient() {
    }

    @Builder
    public SaveOrderClient(int id, String firstName, String lastName,
                           String email, String phoneNumber, int productId, int quantity, int finalPrice) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.productId = productId;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
    }
}
