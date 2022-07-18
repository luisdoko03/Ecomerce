package com.example.onlineshop.dto;

import com.example.onlineshop.entity.Category;
import lombok.Data;

@Data
public class SaveProductRequest {

    private int id;
    private String title;
    private int price;
    private int quantity;
    private String description;
    private String photoUrl;
    private int categoryId;
    private boolean featured;

}
