package com.example.onlineshop.entity;
import lombok.Data;

import javax.persistence.*;
@Entity
@Data
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    private Integer price;

    private Integer quantity;

    private String description;

    @Column(name = "photo_url")
    private String photoUrl;

    private boolean featured;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
    @Column(name = "category_id")
    private int categoryId;
}

