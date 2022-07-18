package com.example.onlineshop.dto;

import lombok.Data;

@Data
public class SaveBlogRequest {
    private int id;
    private String title;
    private String author;
    private String content;
    private String photo;
}
