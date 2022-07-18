package com.example.onlineshop.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "final_price")
    private int finalPrice;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;
    @Column(name = "client_id")
    private int clientId;
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
    private int quantity;
    @Column(name = "product_id")
    private int productId;
}
