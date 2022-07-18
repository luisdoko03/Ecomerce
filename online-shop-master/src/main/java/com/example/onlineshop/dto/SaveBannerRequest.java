package com.example.onlineshop.dto;

import lombok.Data;

@Data
public class SaveBannerRequest {
    private int id;
    private String name;
    private String photoUrl;

}
